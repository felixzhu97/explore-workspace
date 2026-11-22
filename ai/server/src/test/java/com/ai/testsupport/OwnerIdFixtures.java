package com.ai.testsupport;

/** Shared owner-key test constants aligned with {@code c:} client partition prefix. */
public final class OwnerIdFixtures {

  public static final String CLIENT_OWNER_KEY = "c:client-1";
  public static final String CLIENT_BARE_UUID = "11111111-1111-1111-1111-111111111111";
  public static final String CLIENT_FULL_KEY = "c:" + CLIENT_BARE_UUID;

  private OwnerIdFixtures() {}
}
