package com.iam.identity.infra.persistence;

import com.iam.common.domain.model.ResourceName;
import com.iam.identity.domain.model.Role;
import com.iam.identity.domain.model.RoleId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface SpringDataRoleRepository extends JpaRepository<Role, RoleId> {

  Optional<Role> findByName(String name);

  Optional<Role> findByResourceName(ResourceName resourceName);

  @Query(
      """
      select r from Role r
      join RoleAssignment ur on ur.id.roleId = r.id.value
      where ur.id.userId.value = :userId
      """)
  List<Role> findRolesByUserId(@Param("userId") String userId);
}
