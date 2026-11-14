package com.iam.audit.infra.persistence;

import com.iam.audit.domain.model.AdminActivity;
import com.iam.audit.domain.model.AdminActivityId;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataAdminActivityRepository extends JpaRepository<AdminActivity, AdminActivityId> {

  List<AdminActivity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
