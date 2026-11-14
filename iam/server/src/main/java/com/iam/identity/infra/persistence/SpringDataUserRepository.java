package com.iam.identity.infra.persistence;

import com.iam.identity.domain.model.User;
import com.iam.identity.domain.model.UserId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataUserRepository extends JpaRepository<User, UserId> {

  Optional<User> findByUsername(String username);

  Optional<User> findByExternalIdValue(String externalId);
}
