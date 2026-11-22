package com.ai.common.domain.model;

import com.explore.kernel.AbstractEmbeddable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Owner-aware entity with a validated name that can be turned off. */
@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public abstract class AbstractToggleableNamedOwnerEntity<IdT extends AbstractEmbeddable>
    extends AbstractOwnerAwareEntity<IdT> {

  @NotBlank
  @Size(max = 120)
  @Column(nullable = false, length = 120)
  protected String name;

  @Column(nullable = false)
  protected boolean enabled;

  protected AbstractToggleableNamedOwnerEntity(IdT id, String ownerId, String name) {
    super(id, OwnerId.parseKey(ownerId));
    this.name = DomainStrings.requireName(name);
    this.enabled = true;
  }

  /** Renames the entity. */
  protected void rename(String nextName) {
    this.name = DomainStrings.requireName(nextName);
  }

  /** Turns the entity off. */
  public void disable() {
    this.enabled = false;
  }
}
