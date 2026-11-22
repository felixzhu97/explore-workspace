package com.ai.common.domain.model;

import com.explore.kernel.AbstractEmbeddable;
import com.explore.kernel.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Mutable entity partitioned by owner_id. */
@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public abstract class AbstractOwnerAwareEntity<IdT extends AbstractEmbeddable>
    extends AbstractEntity<IdT> {

  @NotNull
  @Column(nullable = false, length = 80)
  protected OwnerId ownerId;

  protected AbstractOwnerAwareEntity(IdT id, OwnerId ownerId) {
    super(id);
    this.ownerId = Objects.requireNonNull(ownerId, "ownerId");
  }

  /** Returns the persisted owner_id value (c:… or u:…). */
  public String getOwnerIdValue() {
    return ownerId.getValue();
  }
}
