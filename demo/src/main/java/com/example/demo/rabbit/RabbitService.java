package com.example.demo.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.AuditLogRepo;
import com.example.demo.server.AuditLogger;
import com.example.demo.server.ValidatorService;
import com.example.demo.utils.rabbit.DirectConsumer;
import com.example.demo.utils.rabbit.DirectPublisher;
import com.example.demo.utils.rabbit.RabbitEvent;

@Service
public class RabbitService {

    /*Aquí se encuentran todos los eventos que envía y recibe el microservicio*/

    @Autowired
    DirectPublisher directPublisher;
    @Autowired
    ValidatorService validatorService;
    @Autowired
    AuditLogger auditLogger;
    @Autowired
    AuditLogRepo auditLogRepo;
    @Autowired
    DirectConsumer directConsumer;

    void createAuditLog(RabbitEvent rabbitEvent){
     
    }

}
