package com.iam.audit.infra.persistence;

import com.iam.audit.domain.model.DataAccessLog;
import com.iam.audit.domain.model.DataAccessLogId;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataDataAccessLogRepository
    extends JpaRepository<DataAccessLog, DataAccessLogId> {

  List<DataAccessLog> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
