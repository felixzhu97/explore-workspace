package com.ai.scim.mapper;

import com.ai.account.domain.model.Account;
import com.ai.account.domain.model.ContactEmail;
import com.ai.scim.service.ScimAccountProfile;
import com.explore.provisioning.ScimEmails;
import com.explore.provisioning.ScimMeta;
import com.unboundid.scim2.common.types.UserResource;
import org.springframework.stereotype.Component;

/** Maps accounts to SCIM User resources and back; holds no business rules. */
@Component
public class ScimAccountMapper {

  public static final String RESOURCE_TYPE = "User";

  /** Maps an account to a SCIM User whose {@code externalId} is the IAM user id. */
  public UserResource toResource(Account account, String usersLocation) {
    UserResource resource = new UserResource();
    resource.setId(account.getId().toString());
    resource.setExternalId(account.getSubject());
    resource.setUserName(account.getUserName());
    resource.setDisplayName(account.getDisplayName());
    resource.setActive(account.isActive());
    if (account.getEmail() != null) {
      resource.setEmails(ScimEmails.work(account.getEmail().getValue()));
    }
    resource.setMeta(
        ScimMeta.create(
            RESOURCE_TYPE,
            usersLocation + "/" + account.getId(),
            account.getCreatedAt(),
            account.getUpdatedAt(),
            account.getVersion() == null ? 0 : account.getVersion()));
    return resource;
  }

  /** Reads the desired state; an invalid email fails instead of being dropped. */
  public ScimAccountProfile toProfile(UserResource resource) {
    String email = ScimEmails.primary(resource.getEmails());
    return new ScimAccountProfile(
        resource.getExternalId(),
        resource.getUserName(),
        email == null || email.isBlank() ? null : new ContactEmail(email),
        resource.getDisplayName(),
        resource.getActive() == null || resource.getActive());
  }
}
