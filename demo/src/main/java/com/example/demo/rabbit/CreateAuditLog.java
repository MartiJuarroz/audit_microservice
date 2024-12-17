package com.example.demo.rabbit;

import com.example.demo.model.ExtraInfo;
import com.example.demo.rabbit.dto.ExtraInfoDTO;
import com.example.demo.repositories.ExtraInfoRepo;
import com.example.demo.services.dto.ListExtraInfoDTO;
import com.example.demo.utils.errors.ModuleInvalidError;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AuditLog;
import com.example.demo.model.ModuleMicroservice;
import com.example.demo.rabbit.dto.CreateAuditLogData;
import com.example.demo.repositories.AuditLogRepo;
import com.example.demo.repositories.ModuleMicroserviceRepo;
import com.example.demo.server.AuditLogger;
import com.example.demo.server.ValidatorService;
import com.example.demo.utils.errors.RecordNotFoundExcepcion;
import com.example.demo.utils.rabbit.DirectConsumer;
import com.example.demo.utils.rabbit.RabbitEvent;

import java.util.List;

@Service
public class CreateAuditLog {

    // @Autowired
    // ValidatorService validatorService;

    @Autowired
    AuditLogger auditLogger;

    @Autowired
    DirectConsumer directConsumer;

    @Autowired
    AuditLogRepo auditLogRepo;

    @Autowired
    ModuleMicroserviceRepo moduleMicroserviceRepo;

    @Autowired
    ExtraInfoRepo extraInfoRepo;

    public void init() {
        directConsumer.init("audit", "audit")
                .addProcessor("create_auditlog", this::createAuditLog)
                .start();
    }

    void createAuditLog(RabbitEvent rabbitEvent){
        CreateAuditLogData createAuditLogEvent = CreateAuditLogData.fromJson(rabbitEvent.message.toString());
        try{
            AuditLog log = new AuditLog();
            ModuleMicroservice module = moduleMicroserviceRepo.findOneByName(rabbitEvent.exchange);
            if (module == null || !module.isActive() || !module.isAudit()){
                throw new BadRequestException("Auditoría no disponible para exchange enviado");
            }
           // createAuditLogEvent.getExtraInfo()
            log.setModuleId(module.getId());
            log.setAction(createAuditLogEvent.getAction());
            log.setActionTo(createAuditLogEvent.getActionTo());
            log.setActionToId(createAuditLogEvent.getActionToId());
            log.setUserId(createAuditLogEvent.getUserId());
            log.setEntity(createAuditLogEvent.getEntity());
            log.setDescription("Se realizó un " + createAuditLogEvent.getAction() + " sobre la entidad " + createAuditLogEvent.getEntity() + " al id " + createAuditLogEvent.getActionToId());
            AuditLog logSaved = auditLogRepo.save(log);
            List<ExtraInfoDTO> extraInfoEntries = createAuditLogEvent.getExtraInfo();
            if (extraInfoEntries != null) {
                for (ExtraInfoDTO entryDTO : extraInfoEntries) {
                    ExtraInfo info = new ExtraInfo();
                    info.setClave(entryDTO.getClave());
                    info.setValor(entryDTO.getValor());

                    // Asocia cada entrada a la entidad principal (evento) mediante una clave externa
                    info.setAuditLog(logSaved);

                    // Guarda la entrada en la base de datos
                    extraInfoRepo.save(info);
                }
            }
        } catch (Exception e){
            auditLogger.error(e.toString(), e);
        }
    }
}