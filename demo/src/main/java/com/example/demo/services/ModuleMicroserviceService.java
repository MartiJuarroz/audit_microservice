package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ModuleMicroservice;
import com.example.demo.repositories.ModuleMicroserviceRepo;
import com.example.demo.security.TokenService;
import com.example.demo.services.dto.ListModuleMicroservice;
import com.example.demo.services.dto.SaveModuleDTO;
import com.example.demo.utils.errors.RecordNotFoundExcepcion;

@Service
public class ModuleMicroserviceService {

    @Autowired
    ModuleMicroserviceRepo moduleMicroserviceRepository;
    @Autowired
    TokenService tokenService;

    @Transactional
    public List<ListModuleMicroservice> findAll(){
        List<ModuleMicroservice> modules = moduleMicroserviceRepository.findAll();
        return modules.stream().map(module -> ListModuleMicroservice.builder()
                .name(module.getName())
                .audit(module.isAudit())
                .active(module.isActive())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public ListModuleMicroservice findById(String id){
        ModuleMicroservice module = moduleMicroserviceRepository.findById(id).orElseThrow(()->new RecordNotFoundExcepcion("No se encontró el medio de pago"));
        return ListModuleMicroservice.builder()
                .name(module.getName())
                .audit(module.isAudit())
                .active(module.isActive())
                .build();
    }

    @Transactional
    public boolean create(SaveModuleDTO module) {
        ModuleMicroservice mod = new ModuleMicroservice();
        mod.setName(module.getName());
        mod.setAudit(module.isAudit());
        mod.setActive(module.isActive());

        moduleMicroserviceRepository.save(mod);

        return true;
    }

    @Transactional
    public ListModuleMicroservice enable(String id) {
        ModuleMicroservice module = moduleMicroserviceRepository.findById(id).orElseThrow(() -> new RecordNotFoundExcepcion("El medio de pago no fue encontrado"));
        module.setAudit(true);
        moduleMicroserviceRepository.save(module);
        return ListModuleMicroservice.builder()
                .name(module.getName())
                .audit(module.isAudit())
                .active(module.isActive())
                .build();
    }

    @Transactional
    public ListModuleMicroservice disable(String id) {
        ModuleMicroservice module = moduleMicroserviceRepository.findById(id).orElseThrow(() -> new RecordNotFoundExcepcion("El medio de pago no fue encontrado"));
        module.setAudit(false);
        moduleMicroserviceRepository.save(module);
         return ListModuleMicroservice.builder()
                .name(module.getName())
                .audit(module.isAudit())
                .active(module.isActive())
                .build();
    }

    @Transactional
    public ListModuleMicroservice enableModule(String id) {
        ModuleMicroservice module = moduleMicroserviceRepository.findById(id).orElseThrow(() -> new RecordNotFoundExcepcion("El medio de pago no fue encontrado"));
        module.setActive(true);
        moduleMicroserviceRepository.save(module);
        return ListModuleMicroservice.builder()
                .name(module.getName())
                .audit(module.isAudit())
                .active(module.isActive())
                .build();
    }

    @Transactional
    public ListModuleMicroservice deleteModule(String idModule, String token){

        ModuleMicroservice module = moduleMicroserviceRepository.findById(idModule).orElseThrow(() -> new RecordNotFoundExcepcion("El medio de pago no fue encontrado"));
        module.setActive(false);
        moduleMicroserviceRepository.save(module);
        return ListModuleMicroservice.builder()
                .name(module.getName())
                .audit(module.isAudit())
                .active(module.isActive())
                .build();
    }
}
