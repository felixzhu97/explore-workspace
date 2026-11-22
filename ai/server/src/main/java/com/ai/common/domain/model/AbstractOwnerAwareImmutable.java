package com.ai.common.domain.model;

import com.explore.kernel.AbstractEmbeddable;
import com.explore.kernel.AbstractImmutableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Immutable entity partitioned by owner_id. */
@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public abstract class AbstractOwnerAwareImmutable<IdT extends AbstractEmbeddable>
    extends AbstractImmutableEntity<IdT> {

  @NotNull
  @Column(nullable = false, length = 80)
  protected OwnerId ownerId;

  protected AbstractOwnerAwareImmutable(IdT id, OwnerId ownerId) {
    super(id);
    this.ownerId = Objects.requireNonNull(ownerId, "ownerId");
  }

  /** Returns the persisted owner_id value (c:… or u:…). */
  public String getOwnerIdValue() {
    return ownerId.getValue();
  }
}
