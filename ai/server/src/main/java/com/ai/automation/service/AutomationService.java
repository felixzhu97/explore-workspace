package com.ai.automation.service;

import com.ai.account.domain.event.GuestLinkedToAccountEvent;
import com.ai.account.domain.event.OwnerErasureRequestedEvent;
import com.ai.automation.domain.model.AutomationRun;
import com.ai.automation.domain.model.AutomationSchedule;
import com.ai.automation.domain.model.ScheduleId;
import com.ai.automation.domain.model.ScheduleKind;
import com.ai.automation.domain.model.ScheduleTiming;
import com.ai.automation.domain.repository.AutomationRunRepository;
import com.ai.automation.domain.repository.AutomationScheduleRepository;
import com.ai.automation.infra.config.AutomationProperties;
import com.ai.common.domain.model.OwnerId;
import com.ai.pipeline.domain.model.PipelineTemplate;
import com.ai.pipeline.domain.model.PipelineTemplateId;
import com.ai.pipeline.domain.repository.PipelineTemplateRepository;
import com.explore.errors.DomainException;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Manages automation schedules, enforcing per-client limits, valid cron and owned workflows. */
@Service
@EnableConfigurationProperties(AutomationProperties.class)
@RequiredArgsConstructor
public class AutomationService {

  private final AutomationScheduleRepository scheduleRepository;
  private final AutomationRunRepository runRepository;
  private final PipelineTemplateRepository pipelineTemplateRepository;
  private final AutomationProperties properties;

  /** Lists the owner's schedules. */
  public List<AutomationSchedule> listSchedules(String ownerId) {
    return scheduleRepository.findAllByOwnerIdOrderByCreatedAtDesc(OwnerId.parseKey(ownerId));
  }

  /** Lists recent runs of the owner's schedule, capped at 100. */
  public List<AutomationRun> listRuns(String ownerId, String scheduleId, int limit) {
    requireOwned(ownerId, scheduleId);
    int capped = Math.min(Math.max(limit, 1), 100);
    return runRepository.findAllByScheduleIdAndOwnerIdOrderByStartedAtDesc(
        ScheduleId.parseId(scheduleId), OwnerId.parseKey(ownerId), Limit.of(capped));
  }

  /** Creates a schedule for the owner and arms its first run. */
  @Transactional
  public AutomationSchedule createSchedule(
      String ownerId,
      String name,
      ScheduleKind scheduleKind,
      String cronExpression,
      Instant runAt,
      String timezone,
      String pipelineTemplateId,
      String recipientEmail,
      String brief) {
    if (scheduleRepository.countByOwnerId(OwnerId.parseKey(ownerId))
        >= properties.getMaxSchedulesPerClient()) {
      throw DomainException.createLimitExceededError(
          "AUTOMATION_LIMIT_EXCEEDED",
          "Schedule limit reached (" + properties.getMaxSchedulesPerClient() + ")");
    }
    requireWorkflow(ownerId, pipelineTemplateId);
    Instant now = Instant.now();
    AutomationSchedule schedule =
        scheduleKind == ScheduleKind.ONCE
            ? AutomationSchedule.createOneOffSchedule(
                ownerId, name, timezone, pipelineTemplateId, recipientEmail, brief, runAt, now)
            : AutomationSchedule.createSchedule(
                ownerId,
                name,
                cronExpression,
                timezone,
                pipelineTemplateId,
                recipientEmail,
                brief,
                now);
    return scheduleRepository.save(schedule);
  }

  /** Replaces the owner's schedule settings and re-arms its next run. */
  @Transactional
  public AutomationSchedule updateSchedule(
      String ownerId,
      String scheduleId,
      String name,
      ScheduleKind scheduleKind,
      String cronExpression,
      Instant runAt,
      String timezone,
      String pipelineTemplateId,
      String recipientEmail,
      String brief) {
    AutomationSchedule schedule = requireOwned(ownerId, scheduleId);
    requireWorkflow(ownerId, pipelineTemplateId);
    schedule.update(
        name,
        ScheduleTiming.createTiming(scheduleKind, cronExpression, timezone),
        runAt,
        pipelineTemplateId,
        recipientEmail,
        brief,
        Instant.now());
    return scheduleRepository.save(schedule);
  }

  /** Enables or disables the owner's schedule, re-arming the next run when enabled. */
  @Transactional
  public AutomationSchedule setEnabled(String ownerId, String scheduleId, boolean enabled) {
    AutomationSchedule schedule = requireOwned(ownerId, scheduleId);
    if (enabled) {
      schedule.enableSchedule(Instant.now());
    } else {
      schedule.disable();
    }
    return scheduleRepository.save(schedule);
  }

  /** Deletes the owner's schedule. */
  @Transactional
  public void deleteSchedule(String ownerId, String scheduleId) {
    requireOwned(ownerId, scheduleId);
    scheduleRepository.deleteByIdAndOwnerId(
        ScheduleId.parseId(scheduleId), OwnerId.parseKey(ownerId));
  }

  private AutomationSchedule requireOwned(String ownerId, String scheduleId) {
    return scheduleRepository
        .findByIdAndOwnerId(ScheduleId.parseId(scheduleId), OwnerId.parseKey(ownerId))
        .orElseThrow(
            () ->
                DomainException.createNotFoundError(
                    "AUTOMATION_SCHEDULE_NOT_FOUND",
                    "Automation schedule not found: " + scheduleId));
  }

  private void requireWorkflow(String ownerId, String pipelineTemplateId) {
    pipelineTemplateRepository
        .findByIdAndOwnerId(
            PipelineTemplateId.parseId(pipelineTemplateId), OwnerId.parseKey(ownerId))
        .filter(PipelineTemplate::isRunnable)
        .orElseThrow(
            () ->
                DomainException.createNotFoundError(
                    "PIPELINE_TEMPLATE_NOT_FOUND",
                    "Pipeline template not found: " + pipelineTemplateId));
  }

  @EventListener
  @Transactional
  void handleOwnerErasureRequestedEvent(OwnerErasureRequestedEvent event) {
    runRepository.deleteAllByOwnerId(event.ownerId());
    scheduleRepository.deleteAllByOwnerId(event.ownerId());
  }

  @EventListener
  @Transactional
  void handleGuestLinkedToAccountEvent(GuestLinkedToAccountEvent event) {
    runRepository.reassignOwnerId(event.fromOwnerId(), event.toOwnerId());
    scheduleRepository.reassignOwnerId(event.fromOwnerId(), event.toOwnerId());
  }
}
