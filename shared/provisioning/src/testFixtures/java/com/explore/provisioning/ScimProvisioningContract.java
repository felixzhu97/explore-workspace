package com.explore.provisioning;

/**
 * Requests IAM sends when it pushes a user to a Provisioning Target. IAM tests assert that its
 * client sends exactly these bodies; target tests replay them. The IAM user id travels as {@code
 * externalId}, which the target stores as the account subject.
 */
public final class ScimProvisioningContract {

  /** IAM user id used when a target replays the contract. */
  public static final String IAM_USER_ID = "3f0c9a52-6d1e-4b7a-9c2f-8e5d1a4b7c60";

  /** Pushed {@code userName}. */
  public static final String USER_NAME = "contract.user";

  /** Pushed primary work email. */
  public static final String EMAIL = "contract.user@example.test";

  /** Pushed display name on create. */
  public static final String DISPLAY_NAME = "Contract User";

  /** Pushed display name on replace. */
  public static final String RENAMED_DISPLAY_NAME = "Renamed Contract User";

  private static final String USER =
      """
      {"schemas":["urn:ietf:params:scim:schemas:core:2.0:User"],
       "externalId":"%s",
       "userName":"%s",
       "displayName":"%s",
       "active":true,
       "emails":[{"value":"%s","type":"work","primary":true}]}
      """;

  private ScimProvisioningContract() {}

  /**
   * Returns the {@code filter} query value of the lookup by IAM user id.
   *
   * @param iamUserId IAM user id
   * @return filter expression
   */
  public static String externalIdFilter(String iamUserId) {
    return "externalId eq \"" + iamUserId + "\"";
  }

  /**
   * Returns the body of {@code POST /Users}.
   *
   * @param iamUserId IAM user id
   * @return SCIM JSON
   */
  public static String createUserBody(String iamUserId) {
    return USER.formatted(iamUserId, USER_NAME, DISPLAY_NAME, EMAIL);
  }

  /**
   * Returns the body of {@code PUT /Users/{id}} after the display name changed.
   *
   * @param iamUserId IAM user id
   * @return SCIM JSON
   */
  public static String replaceUserBody(String iamUserId) {
    return USER.formatted(iamUserId, USER_NAME, RENAMED_DISPLAY_NAME, EMAIL);
  }

  /**
   * Returns the body of {@code PATCH /Users/{id}} that changes only {@code active}.
   *
   * @param active new state
   * @return SCIM JSON
   */
  public static String setActiveBody(boolean active) {
    return """
        {"schemas":["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
         "Operations":[{"op":"replace","path":"active","value":%s}]}
        """
        .formatted(active);
  }
}
