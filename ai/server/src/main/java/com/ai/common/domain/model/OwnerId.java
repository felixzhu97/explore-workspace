package com.ai.common.domain.model;

import java.util.Objects;
import lombok.Value;

/**
 * Data partition key: guest browser {@code c:{clientId}} or signed-in account {@code
 * u:{accountId}}.
 */
@Value
public class OwnerId {
  String value;

  public static final String CLIENT_PREFIX = "c:";
  public static final String ACCOUNT_PREFIX = "u:";

  /** Rows without a visitor, such as session-less metrics; never matches a live visitor. */
  public static final OwnerId UNOWNED = new OwnerId("c:legacy-orphan");

  public OwnerId(String value) {
    Objects.requireNonNull(value, "value");
    String trimmed = value.trim();
    if (trimmed.isEmpty()) {
      throw new IllegalArgumentException("owner id is required");
    }
    if (!trimmed.startsWith(CLIENT_PREFIX) && !trimmed.startsWith(ACCOUNT_PREFIX)) {
      throw new IllegalArgumentException("owner id must start with c: or u:");
    }
    if (trimmed.length() <= 2) {
      throw new IllegalArgumentException("owner id id is required");
    }
    value = trimmed;
    this.value = value;
  }

  /** Creates a guest owner id prefixed with {@code c:} for the given client id. */
  public static OwnerId createClientKey(String clientId) {
    if (clientId == null || clientId.isBlank()) {
      throw new IllegalArgumentException("clientId is required");
    }
    return new OwnerId(CLIENT_PREFIX + clientId.trim());
  }

  /** Creates a signed-in owner id prefixed with {@code u:} for the given account id. */
  public static OwnerId createAccountKey(String accountId) {
    if (accountId == null || accountId.isBlank()) {
      throw new IllegalArgumentException("accountId is required");
    }
    return new OwnerId(ACCOUNT_PREFIX + accountId.trim());
  }

  /** Wraps a stored owner id value. */
  public static OwnerId parseKey(String text) {
    return new OwnerId(text);
  }

  /** Tells whether the key belongs to a signed-in account. */
  public boolean isAccount() {
    return value.startsWith(ACCOUNT_PREFIX);
  }

  /** Tells whether the key belongs to a guest client. */
  public boolean isClient() {
    return value.startsWith(CLIENT_PREFIX);
  }

  /** Rejects any merge other than a live guest's rows moving into a signed-in account. */
  public void requireMergeableInto(OwnerId target) {
    Objects.requireNonNull(target, "target");
    if (!isClient() || equals(UNOWNED) || !target.isAccount()) {
      throw new IllegalArgumentException("Only guest data can move into a signed-in account");
    }
  }
}
