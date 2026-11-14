package com.explore.provisioning;

import com.unboundid.scim2.common.types.Email;
import java.util.List;

/**
 * Maps between a single account email and the SCIM User {@code emails} attribute.
 */
public final class ScimEmails {

  private ScimEmails() {}

  /**
   * Returns the primary email, or the first one when none is marked primary.
   *
   * @param emails SCIM {@code emails}, may be {@code null}
   * @return email address, or {@code null} when there is none
   */
  public static String primary(List<Email> emails) {
    if (emails == null || emails.isEmpty()) {
      return null;
    }
    return emails.stream()
        .filter(email -> Boolean.TRUE.equals(email.getPrimary()))
        .findFirst()
        .orElse(emails.get(0))
        .getValue();
  }

  /**
   * Wraps an address as the single primary work email.
   *
   * @param address email address
   * @return SCIM {@code emails} value
   */
  public static List<Email> work(String address) {
    return List.of(new Email().setValue(address).setType("work").setPrimary(true));
  }
}
