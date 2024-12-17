package com.example.demo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AuditLog;

@Repository
public interface AuditLogRepo extends JpaRepository<AuditLog, String> {

    @Query(value = "SELECT al.*, ex.clave, ex.valor " +
            " FROM audit_log al" +
            " INNER JOIN extra_info ex " +
            " ON al.id = ex.audit_log_id " +
            " WHERE (:action = al.action OR :action IS NULL) AND " +
            "(:entity IS NULL OR :entity = al.entity) AND " +
            "(:userId IS NULL OR :userId = al.user_id) AND " +
            "(:moduleId IS NULL OR :moduleId = al.module_id) AND " +
            "(:dateFrom IS NULL OR :dateFrom <= al.date) AND " +
            "(:dateTo IS NULL OR :dateTo >= al.date) " +
            " GROUP BY al.id " +
            "ORDER BY al.date DESC "
            , nativeQuery = true
    )
    List<AuditLog> findAuditLogs(@Param("action") String action,
                                @Param("entity") String entity,
                                @Param("dateFrom") LocalDateTime dateFrom,
                                @Param("dateTo") LocalDateTime dateTo,
                                @Param("userId") String userId,
                                @Param("moduleId") String moduleId);

}
