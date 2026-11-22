package com.iam.common.domain.model;

/** Policy evaluation effect (Permit or Deny). */
public enum Effect {
  PERMIT,
  DENY;

  /**
   * Parses an effect from its string representation.
   *
   * @param raw effect name
   * @return matching effect
   */
  public static Effect parseEffect(String raw) {
    if (raw == null || raw.isBlank()) {
      throw new IllegalArgumentException("effect must not be blank");
    }
    String normalized = raw.trim().toUpperCase();
    if ("ALLOW".equals(normalized)) {
      return PERMIT;
    }
    return Effect.valueOf(normalized);
  }
}
