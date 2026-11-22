package com.iam.federation.infra.persistence;

import com.explore.kernel.AbstractEmbeddable;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/** Strongly-typed ID for {@link SigningKey}. */
@Embeddable
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(staticName = "createId")
final class SigningKeyId extends AbstractEmbeddable {

  @NonNull private String value;

  /** Creates a new random id. */
  static SigningKeyId generateId() {
    return createId(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return value;
  }
}
