package com.ai.pipeline.domain.repository;

import com.ai.common.domain.model.OwnerId;
import com.ai.common.domain.repository.OwnerPartitionedRepository;
import com.ai.pipeline.domain.model.CustomAgent;
import com.ai.pipeline.domain.model.CustomAgentId;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/** Persists custom agents scoped by owner, with type keys unique per owner. */
public interface CustomAgentRepository
    extends OwnerPartitionedRepository<CustomAgent, CustomAgentId> {

  /** Finds the owner's custom agent by id. */
  Optional<CustomAgent> findByIdAndOwnerId(CustomAgentId id, OwnerId ownerId);

  /** Lists the owner's custom agents by name. */
  List<CustomAgent> findAllByOwnerIdOrderByNameAsc(OwnerId ownerId);

  /** Lists the owner's custom agents that are on, by name. */
  List<CustomAgent> findAllByOwnerIdAndEnabledTrueOrderByNameAsc(OwnerId ownerId);

  /** Tells whether the owner already has a custom agent with the type key. */
  boolean existsByOwnerIdAndAgentType(OwnerId ownerId, String agentType);

  /** Tells whether another of the owner's custom agents already has the type key. */
  boolean existsByOwnerIdAndAgentTypeAndIdNot(
      OwnerId ownerId, String agentType, CustomAgentId id);

  /** Tells whether the owner has a custom agent with the type key other than {@code excludeId}. */
  default boolean existsByOwnerIdAndAgentTypeIgnoringId(
      OwnerId ownerId, String agentType, CustomAgentId excludeId) {
    return excludeId == null
        ? existsByOwnerIdAndAgentType(ownerId, agentType)
        : existsByOwnerIdAndAgentTypeAndIdNot(ownerId, agentType, excludeId);
  }

  /** Saves the agent and returns the stored copy. */
  CustomAgent save(CustomAgent agent);

  /** Deletes the owner's custom agent by id. */
  @Transactional
  void deleteByIdAndOwnerId(CustomAgentId id, OwnerId ownerId);
}
