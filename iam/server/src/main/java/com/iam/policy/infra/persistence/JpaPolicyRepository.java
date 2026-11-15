package com.iam.policy.infra.persistence;

import com.iam.common.domain.model.ResourceName;
import com.iam.policy.domain.model.Policy;
import com.iam.policy.domain.model.PolicyId;
import com.iam.policy.domain.model.PolicyAssignment;
import com.iam.policy.domain.repository.PolicyRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
class JpaPolicyRepository implements PolicyRepository {

  private final SpringDataPolicyRepository documentRepository;
  private final SpringDataPolicyAssignmentRepository attachmentRepository;

  JpaPolicyRepository(
      SpringDataPolicyRepository documentRepository,
      SpringDataPolicyAssignmentRepository attachmentRepository) {
    this.documentRepository = documentRepository;
    this.attachmentRepository = attachmentRepository;
  }

  @Override
  public Policy save(Policy policy) {
    return documentRepository.save(policy);
  }

  @Override
  public Optional<Policy> findById(String id) {
    return documentRepository.findById(PolicyId.createId(id));
  }

  @Override
  public List<Policy> findAll() {
    return documentRepository.findAll();
  }

  @Override
  public PolicyAssignment saveAttachment(PolicyAssignment attachment) {
    return attachmentRepository.save(attachment);
  }

  @Override
  public List<Policy> findAttachedToPrincipal(ResourceName principalArn) {
    List<Policy> policies = new ArrayList<>();
    for (PolicyAssignment attachment : attachmentRepository.findByPrincipalArn(principalArn)) {
      findById(attachment.getPolicyId()).ifPresent(policies::add);
    }
    return policies;
  }
}
