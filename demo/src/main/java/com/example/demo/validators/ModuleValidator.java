package com.example.demo.validators;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ModuleMicroservice;
import com.example.demo.repositories.ModuleMicroserviceRepo;

import lombok.Data;

@Data
@Service
public class ModuleValidator {

    @Autowired
    ModuleMicroserviceRepo moduleMicroserviceRepo;

    public void validateNameModule(ModuleMicroservice module){
        HashMap<String, String> errors = new HashMap<>();

        // modules = moduleMicroserviceRepo.findAll()

        // if()
        
    }

    // public void validateEnablePaymentMethodUser(PaymentMethod paymentMethod){

    //     HashMap<String, String> errors = new HashMap<>();

    //     if(paymentMethod.isDefault()){
    //         errors.put("Default","El medio de pago es un medio de pago por defecto para todos los usuarios.");
    //     }

    //     if(!paymentMethod.isEnabled()){
    //         errors.put("Disabled","El medio de pago seleccionado se encuentra deshabilitado por el momento.");
    //     }

    //     if(!errors.isEmpty()) {
    //         throw new PaymentMethodInvalidError(errors);
    //     }
    // }

    // public void validateOrderPaymentMethod(OrderPaymentMethod orderPaymentMethod){

    //     HashMap<String, String> errors = new HashMap<>();

    //     if(orderPaymentMethod == null){
    //         throw new RecordNotFoundExcepcion("No fue encontrada una orden con un medio de pago pendiente con ese id");
    //     }

    //     if(orderPaymentMethod.getPaymentMethodId() != null){
    //         errors.put("payment method", "La orden seleccionada ya posee un medio de pago establecido.");
    //     }

    //     if(!errors.isEmpty()) {
    //         throw new PaymentMethodInvalidError(errors);
    //     }
    // }
}
