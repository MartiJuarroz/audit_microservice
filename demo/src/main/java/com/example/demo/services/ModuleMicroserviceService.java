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
    //@Autowired
    // PaymentMethodValidator paymentMethodValidator;

    @Transactional
    public List<ListModuleMicroservice> findAll(){
        List<ModuleMicroservice> modules = moduleMicroserviceRepository.findAll();
        return modules.stream().map(module -> ListModuleMicroservice.builder()
                .name(module.getName())
                .audit(module.isAudit())
                .active(module.isActive())
                .build()).collect(Collectors.toList());
    }

    // @Transactional
    // public List<ListPaymentMethods> findAllByUser(String id){
    //     List<PaymentMethod> pmus = paymentMethodRepository.findAllByUser(id);
    //     return pmus.stream().map(pm -> ListPaymentMethods.builder()
    //             .name(pm.getName())
    //             .enabled(pm.isEnabled())
    //             .isDefault(pm.isDefault())
    //             .build()).collect(Collectors.toList());
    // }

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

    // @Transactional
    // public void selectPaymentMethod(String orderId, String paymentMethodId, String token){
    //     OrderPaymentMethod orderPaymentMethod = orderPaymentMethodRepo.findByOrderId(orderId);
    //     String idUser = tokenService.getUser(token).id;
    //     PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow(() -> new RecordNotFoundExcepcion("No se encontro el medio de pago."));

    //     List<PaymentMethod> pmsHabilitados = paymentMethodRepository.findAllByUser(idUser);
    //     if(!pmsHabilitados.contains(paymentMethod)){
    //         throw new UserUnauthorizedException("El usuario no tiene el medio de pago seleccionado habilitado");
    //     }

    //     paymentMethodValidator.validateOrderPaymentMethod(orderPaymentMethod);

    //     orderPaymentMethod.setPaymentMethodId(paymentMethodId);
    //     orderPaymentMethodRepo.save(orderPaymentMethod);

    //     PublishPaymentDefinedData publishPaymentDefinedData = new PublishPaymentDefinedData(orderId, paymentMethodId);
    //     publishPaymentDefinedData.publishIn(publishPaymentDefined, "order", "order");
    // }

    // @Transactional
    // public void enablePaymentMethodUser(String idPaymentMethod, String token, EnableCreditCardDTO dataCard){

    //     String idUser = tokenService.getUser(token).id;
    //     PaymentMethod paymentMethod = paymentMethodRepository.findById(idPaymentMethod).orElseThrow(() -> new RecordNotFoundExcepcion("No se encontro el medio de pago."));

    //     paymentMethodValidator.validateEnablePaymentMethodUser(paymentMethod);

    //     if(paymentMethodUserRepo.findByUserIdAndPaymentMethodId(idUser, Long.parseLong(idPaymentMethod)).isEmpty()){
    //         PaymentMethodUser pmu = new PaymentMethodUser();
    //         pmu.setPaymentMethodId(Long.parseLong(idPaymentMethod));
    //         pmu.setUserId(idUser);
    //         paymentMethodUserRepo.save(pmu);

    //         if(Objects.equals(paymentMethod.getName(), "Tarjeta de crédito")){
    //             DataCreditCard dataCreditCard = new DataCreditCard();
    //             dataCreditCard.setCardNumber(dataCard.getNumber());
    //             dataCreditCard.setSecurityCode(dataCard.getSecurityCode());
    //             dataCreditCard.setUserId(idUser);
    //             dataCreditCardRepo.save(dataCreditCard);
    //         }
    //     }
    // }

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

        // String idUser = tokenService.getUser(token).id;
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
