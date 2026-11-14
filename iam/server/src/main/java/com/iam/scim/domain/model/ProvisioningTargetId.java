package com.iam.scim.domain.model;

import com.explore.kernel.AbstractEmbeddable;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/** Strongly-typed ID for {@link ProvisioningTarget}. */
@Embeddable
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(staticName = "createId")
public final class ProvisioningTargetId extends AbstractEmbeddable {

  @NonNull private String value;

  /** Creates a new random id. */
  public static ProvisioningTargetId generateId() {
    return createId(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return value;
  }
}
