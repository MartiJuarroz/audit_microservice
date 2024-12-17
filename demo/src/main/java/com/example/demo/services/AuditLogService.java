package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.ExtraInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.AuditLog;
import com.example.demo.repositories.AuditLogRepo;
import com.example.demo.security.TokenService;
import com.example.demo.services.dto.ListAuditLogs;

@Service
public class AuditLogService {

    @Autowired
    AuditLogRepo auditLogRepository;
    @Autowired
    TokenService tokenService;

    @Transactional
    public List<ListAuditLogs> findAuditLogs(String action, String entity, String dateFrom, String dateTo, String userId, String moduleId) {
        LocalDateTime parsedDateFrom = (dateFrom == null) ? null : LocalDateTime.parse(dateFrom);
        LocalDateTime parsedDateTo = (dateTo == null) ? null : LocalDateTime.parse(dateTo);

        List<AuditLog> logs = auditLogRepository.findAuditLogs(action, entity, parsedDateFrom, parsedDateTo, userId, moduleId);

        return logs == null || logs.isEmpty()  ? null : logs.stream().map(log -> {
            HashMap<String, String> inf = new HashMap<>();
            if (log == null) {
                return null;
            }
            for (ExtraInfo info : log.getExtraInfo()){
                inf.put(info.getClave(), info.getValor());
            }
            ListAuditLogs.ListAuditLogsBuilder builder = ListAuditLogs.builder()
                    .action(log.getAction())
                    .actionTo(log.getActionTo())
                    .actionToId(log.getActionToId())
                    .date(log.getDate().toString())
                    .userId(log.getUserId())
                    .entity(log.getEntity())
                    .description(log.getDescription())
                    .moduleId(log.getModuleId())
                    .extraInfo(inf);

            return builder.build();
        }).collect(Collectors.toList());
    }

}
