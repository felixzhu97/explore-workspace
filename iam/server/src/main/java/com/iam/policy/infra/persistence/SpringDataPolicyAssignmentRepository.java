package com.iam.policy.infra.persistence;

import com.iam.common.domain.model.ResourceName;
import com.iam.policy.domain.model.PolicyAssignment;
import com.iam.policy.domain.model.PolicyAssignmentId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataPolicyAssignmentRepository
    extends JpaRepository<PolicyAssignment, PolicyAssignmentId> {

  List<PolicyAssignment> findByPrincipalArn(ResourceName principalArn);
}
