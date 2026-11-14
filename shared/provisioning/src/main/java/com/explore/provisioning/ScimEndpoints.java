package com.explore.provisioning;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * SCIM endpoint paths and absolute URL helpers for the current request.
 */
public final class ScimEndpoints {

  /** SCIM base path. */
  public static final String BASE = "/scim/v2";

  /** Users endpoint path. */
  public static final String USERS = BASE + "/Users";

  /** Groups endpoint path. */
  public static final String GROUPS = BASE + "/Groups";

  private ScimEndpoints() {}

  /**
   * Returns the absolute Users URL for a service without groups.
   *
   * @return locations without a groups URL
   */
  public static ScimLocations currentLocations() {
    return ScimLocations.usersOnly(root() + USERS);
  }

  /**
   * Returns the absolute Users and Groups URLs.
   *
   * @return locations with both URLs
   */
  public static ScimLocations currentLocationsWithGroups() {
    String root = root();
    return new ScimLocations(root + USERS, root + GROUPS);
  }

  private static String root() {
    return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
  }
}
