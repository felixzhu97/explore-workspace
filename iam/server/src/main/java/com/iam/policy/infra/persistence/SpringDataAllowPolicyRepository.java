package com.iam.policy.infra.persistence;

import com.iam.policy.domain.model.AllowPolicy;
import com.iam.policy.domain.model.AllowPolicyId;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataAllowPolicyRepository extends JpaRepository<AllowPolicy, AllowPolicyId> {}
