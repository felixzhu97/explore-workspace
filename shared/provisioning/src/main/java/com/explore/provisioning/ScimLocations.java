package com.explore.provisioning;

/**
 * Absolute endpoint URLs used for {@code meta.location} and member {@code $ref}.
 *
 * @param users URL of {@code /scim/v2/Users}
 * @param groups URL of {@code /scim/v2/Groups}, or {@code null} when the service has no groups
 */
public record ScimLocations(String users, String groups) {

  /**
   * Returns the locations of a service that only provisions users.
   *
   * @param users URL of {@code /scim/v2/Users}
   * @return locations without a groups URL
   */
  public static ScimLocations usersOnly(String users) {
    return new ScimLocations(users, null);
  }
}
