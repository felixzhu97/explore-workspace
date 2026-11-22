package com.ai.pipeline.service;

import com.ai.account.domain.event.GuestLinkedToAccountEvent;
import com.ai.account.domain.event.OwnerErasureRequestedEvent;
import com.ai.common.domain.model.DomainStrings;
import com.ai.common.domain.model.OwnerId;
import com.ai.pipeline.domain.model.PipelineTemplate;
import com.ai.pipeline.domain.model.PipelineTemplateId;
import com.ai.pipeline.domain.repository.PipelineTemplateRepository;
import com.explore.errors.DomainException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Manages a client's saved pipeline templates, including copies of built-in catalog templates. */
@Service
@Transactional
@RequiredArgsConstructor
public class PipelineTemplateService {

  private final PipelineTemplateRepository repository;

  /** Lists the built-in pipeline templates. */
  public List<BuiltinPipelineTemplate> listTemplates(String language) {
    return PipelineTemplateCatalog.listAll(language);
  }

  /** Lists the owner's saved pipelines. */
  public List<PipelineTemplate> listLibrary(String ownerId) {
    return repository.findAllByOwnerIdOrderByNameAsc(OwnerId.parseKey(ownerId));
  }

  /** Copies a built-in template into the owner's library. */
  public PipelineTemplate createFromTemplate(String ownerId, String templateId, String language) {
    BuiltinPipelineTemplate template =
        PipelineTemplateCatalog.findById(templateId, language)
            .orElseThrow(
                () -> new IllegalArgumentException("Unknown pipeline template: " + templateId));
    return createTemplate(
        ownerId,
        findNextAvailableName(ownerId, template.name()),
        template.description(),
        template.agentTypes(),
        template.topic(),
        template.brief(),
        template.id());
  }

  /** Saves a new Pipeline Template for the owner. */
  public PipelineTemplate createTemplate(
      String ownerId,
      String name,
      String description,
      List<String> agentTypes,
      String topic,
      String brief,
      String builtinTemplateId) {
    assertNameAvailable(ownerId, name, null);
    PipelineTemplate template =
        PipelineTemplate.createTemplate(
            ownerId, name, description, agentTypes, topic, brief, builtinTemplateId);
    return repository.save(template);
  }

  /** Replaces the owner's Pipeline Template content. */
  public PipelineTemplate updateTemplate(
      String ownerId,
      String id,
      String name,
      String description,
      List<String> agentTypes,
      String topic,
      String brief) {
    PipelineTemplate template = findOwned(ownerId, id);
    assertNameAvailable(ownerId, name, template.getId());
    template.update(name, description, agentTypes, topic, brief);
    return repository.save(template);
  }

  /** Enables or disables the owner's Pipeline Template. */
  public PipelineTemplate setEnabled(String ownerId, String id, boolean enabled) {
    PipelineTemplate template = findOwned(ownerId, id);
    template.updateEnabledState(enabled);
    return repository.save(template);
  }

  /** Deletes the owner's saved pipeline. */
  public void deleteTemplate(String ownerId, String id) {
    findOwned(ownerId, id);
    repository.deleteByIdAndOwnerId(PipelineTemplateId.parseId(id), OwnerId.parseKey(ownerId));
  }

  /** Returns the owner's saved pipeline. */
  public PipelineTemplate getTemplate(String ownerId, String id) {
    return findOwned(ownerId, id);
  }

  private PipelineTemplate findOwned(String ownerId, String id) {
    return repository
        .findByIdAndOwnerId(PipelineTemplateId.parseId(id), OwnerId.parseKey(ownerId))
        .orElseThrow(
            () ->
                DomainException.createNotFoundError(
                    "PIPELINE_TEMPLATE_NOT_FOUND", "Pipeline template not found: " + id));
  }

  private void assertNameAvailable(String ownerId, String name, PipelineTemplateId excludeId) {
    String normalized = DomainStrings.normalizeName(name);
    if (repository.existsByOwnerIdAndNameIgnoringId(
        OwnerId.parseKey(ownerId), normalized, excludeId)) {
      throw DomainException.createConflictError(
          "PIPELINE_TEMPLATE_NAME_CONFLICT",
          "Pipeline template name already exists: " + normalized);
    }
  }

  private String findNextAvailableName(String ownerId, String baseName) {
    OwnerId owner = OwnerId.parseKey(ownerId);
    return DomainStrings.copyNameCandidates(baseName, DomainStrings.DEFAULT_NAME_MAX)
        .filter(name -> !repository.existsByOwnerIdAndNameIgnoringId(owner, name, null))
        .findFirst()
        .orElseGet(
            () ->
                DomainStrings.copyName(
                    baseName,
                    PipelineTemplateId.generateId().toString().substring(0, 8),
                    DomainStrings.DEFAULT_NAME_MAX));
  }

  @EventListener
  void handleOwnerErasureRequestedEvent(OwnerErasureRequestedEvent event) {
    repository.deleteAllByOwnerId(event.ownerId());
  }

  @EventListener
  void handleGuestLinkedToAccountEvent(GuestLinkedToAccountEvent event) {
    repository.reassignOwnerId(event.fromOwnerId(), event.toOwnerId());
  }
}
