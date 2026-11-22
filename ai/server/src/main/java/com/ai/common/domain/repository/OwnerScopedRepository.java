package com.ai.common.domain.repository;

import com.ai.common.domain.model.OwnerId;
import com.explore.kernel.AbstractEmbeddable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository of named aggregates partitioned by owner, with names unique per owner. Every read and
 * delete takes the owner key, so one owner never sees another owner's rows.
 */
@NoRepositoryBean
public interface OwnerScopedRepository<E, I extends AbstractEmbeddable>
    extends OwnerPartitionedRepository<E, I> {

  /** Finds the owner's entity by id. */
  Optional<E> findByIdAndOwnerId(I id, OwnerId ownerId);

  /** Lists the owner's entities by name. */
  List<E> findAllByOwnerIdOrderByNameAsc(OwnerId ownerId);

  /** Tells whether the owner already has an entity with the name. */
  boolean existsByOwnerIdAndName(OwnerId ownerId, String name);

  /** Tells whether another of the owner's entities already has the name. */
  boolean existsByOwnerIdAndNameAndIdNot(OwnerId ownerId, String name, I id);

  /** Tells whether the owner has an entity with the name other than {@code excludeId}. */
  default boolean existsByOwnerIdAndNameIgnoringId(OwnerId ownerId, String name, I excludeId) {
    return excludeId == null
        ? existsByOwnerIdAndName(ownerId, name)
        : existsByOwnerIdAndNameAndIdNot(ownerId, name, excludeId);
  }

  /** Saves the entity and returns the stored copy. */
  <S extends E> S save(S entity);

  /** Deletes the owner's entity by id. */
  @Transactional
  void deleteByIdAndOwnerId(I id, OwnerId ownerId);
}
