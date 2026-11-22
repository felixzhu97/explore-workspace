package com.iam.policy.infra.persistence;

import com.iam.policy.domain.model.Policy;
import com.iam.policy.domain.model.PolicyId;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataPolicyRepository extends JpaRepository<Policy, PolicyId> {}
