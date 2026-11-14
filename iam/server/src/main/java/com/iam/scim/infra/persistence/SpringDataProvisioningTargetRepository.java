package com.iam.scim.infra.persistence;

import com.iam.scim.domain.model.ProvisioningTarget;
import com.iam.scim.domain.model.ProvisioningTargetId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataProvisioningTargetRepository
    extends JpaRepository<ProvisioningTarget, ProvisioningTargetId> {

  Optional<ProvisioningTarget> findByName(String name);

  List<ProvisioningTarget> findByEnabledTrue();
}
