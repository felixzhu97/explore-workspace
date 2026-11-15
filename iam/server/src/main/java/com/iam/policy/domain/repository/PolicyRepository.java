package com.iam.policy.domain.repository;

import com.iam.common.domain.model.ResourceName;
import com.iam.policy.domain.model.Policy;
import com.iam.policy.domain.model.PolicyAssignment;
import java.util.List;
import java.util.Optional;

/** Persistence port for policy documents and attachments. */
public interface PolicyRepository {

  /**
   * Persists a policy document.
   *
   * @param policy policy to store
   * @return stored policy
   */
  Policy save(Policy policy);

  /**
   * Finds a policy by id.
   *
   * @param id policy id
   * @return matching policy when present
   */
  Optional<Policy> findById(String id);

  /**
   * Lists all policy documents.
   *
   * @return all policies
   */
  List<Policy> findAll();

  /**
   * Persists a policy attachment.
   *
   * @param attachment attachment to store
   * @return stored attachment
   */
  PolicyAssignment saveAttachment(PolicyAssignment attachment);

  /**
   * Returns policies attached to the given principal ARN.
   *
   * @param principalArn principal ARN
   * @return attached policies
   */
  List<Policy> findAttachedToPrincipal(ResourceName principalArn);
}
