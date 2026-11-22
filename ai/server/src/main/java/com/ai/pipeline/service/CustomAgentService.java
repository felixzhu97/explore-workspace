package com.ai.pipeline.service;

import com.ai.account.domain.event.GuestLinkedToAccountEvent;
import com.ai.account.domain.event.OwnerErasureRequestedEvent;
import com.ai.common.domain.model.OwnerId;
import com.ai.pipeline.domain.model.CustomAgent;
import com.ai.pipeline.domain.model.CustomAgentId;
import com.ai.pipeline.domain.repository.CustomAgentRepository;
import com.explore.errors.DomainException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Manages a client's custom agent library, enforcing unique type keys per client. */
@Service
@Transactional
@RequiredArgsConstructor
public class CustomAgentService {

  private final CustomAgentRepository repository;

  /** Lists the owner's custom agents. */
  public List<CustomAgent> listLibrary(String ownerId) {
    return repository.findAllByOwnerIdOrderByNameAsc(OwnerId.parseKey(ownerId));
  }

  /** Saves a new agent definition in the owner's library. */
  public CustomAgent createAgent(
      String ownerId,
      String agentType,
      String name,
      String description,
      String systemPrompt,
      List<String> tools) {
    CustomAgent agent =
        CustomAgent.createAgent(ownerId, agentType, name, description, systemPrompt, tools);
    assertTypeAvailable(ownerId, agent.getAgentType(), null);
    return repository.save(agent);
  }

  /** Updates the owner's custom agent. */
  public CustomAgent updateAgent(
      String ownerId,
      String id,
      String name,
      String description,
      String systemPrompt,
      List<String> tools) {
    CustomAgent agent = findOwned(ownerId, id);
    agent.update(name, description, systemPrompt, tools);
    return repository.save(agent);
  }

  /** Enables or disables the owner's custom agent. */
  public CustomAgent setEnabled(String ownerId, String id, boolean enabled) {
    CustomAgent agent = findOwned(ownerId, id);
    agent.updateEnabledState(enabled);
    return repository.save(agent);
  }

  /** Deletes the owner's custom agent. */
  public void deleteAgent(String ownerId, String id) {
    findOwned(ownerId, id);
    repository.deleteByIdAndOwnerId(CustomAgentId.parseId(id), OwnerId.parseKey(ownerId));
  }

  private CustomAgent findOwned(String ownerId, String id) {
    return repository
        .findByIdAndOwnerId(CustomAgentId.parseId(id), OwnerId.parseKey(ownerId))
        .orElseThrow(
            () ->
                DomainException.createNotFoundError(
                    "SAVED_AGENT_NOT_FOUND", "Custom agent not found: " + id));
  }

  private void assertTypeAvailable(String ownerId, String agentType, CustomAgentId excludeId) {
    if (repository.existsByOwnerIdAndAgentTypeIgnoringId(
        OwnerId.parseKey(ownerId), agentType, excludeId)) {
      throw DomainException.createConflictError(
          "SAVED_AGENT_TYPE_CONFLICT", "Agent type key already exists: " + agentType);
    }
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
