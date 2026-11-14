package com.iam.policy.infra.persistence;

import com.iam.policy.domain.model.Permission;
import com.iam.policy.domain.model.PermissionId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataPermissionRepository extends JpaRepository<Permission, PermissionId> {

  Optional<Permission> findByCode(String code);

  boolean existsByCode(String code);
}
