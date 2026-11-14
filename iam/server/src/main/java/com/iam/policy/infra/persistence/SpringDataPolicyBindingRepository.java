package com.iam.policy.infra.persistence;

import com.iam.common.domain.model.ResourceName;
import com.iam.policy.domain.model.PolicyBinding;
import com.iam.policy.domain.model.PolicyBindingId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataPolicyBindingRepository extends JpaRepository<PolicyBinding, PolicyBindingId> {

  List<PolicyBinding> findByPrincipalArn(ResourceName principalArn);
}
