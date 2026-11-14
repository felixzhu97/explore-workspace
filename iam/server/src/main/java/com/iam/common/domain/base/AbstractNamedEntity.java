package com.iam.common.domain.base;

import com.explore.kernel.AbstractEmbeddable;
import com.explore.kernel.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Mutable IAM aggregate identified by a unique human-readable name. */
@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public abstract class AbstractNamedEntity<IdT extends AbstractEmbeddable>
    extends AbstractEntity<IdT> {

  @NotBlank
  @Size(max = 256)
  @Column(nullable = false, unique = true, length = 256)
  private String name;

  /**
   * Subclass constructor supplying identity and name.
   *
   * @param id internal id
   * @param name unique display name
   */
  protected AbstractNamedEntity(IdT id, String name) {
    super(id);
    this.name = DomainStrings.requireNonBlank(name, "name");
  }
}
