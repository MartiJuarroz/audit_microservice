// package com.example.demo.rabbit;

// import com.example.demo.model.OrderPaymentMethod;
// import com.example.demo.rabbit.dto.ConsumePaymentUndefinedData;
// import com.example.demo.repositories.OrderPaymentMethodRepo;
// import com.example.demo.security.TokenService;
// import com.example.demo.server.PaymentMethodLogger;
// import com.example.demo.server.ValidatorService;
// import com.example.demo.utils.rabbit.DirectConsumer;
// import com.example.demo.utils.rabbit.RabbitEvent;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class ConsumePaymentUndefined {

//     @Autowired
//     ValidatorService validatorService;

//     @Autowired
//     PaymentMethodLogger paymentMethodLogger;

//     @Autowired
//     DirectConsumer directConsumer;

//     @Autowired
//     OrderPaymentMethodRepo orderPaymentMethodRepo;

//     public void init() {
//         directConsumer.init("payment_method", "payment_method")
//                 .addProcessor("payment_undefined", this::proccessPaymentUndefined)
//                 .start();
//     }

//     void proccessPaymentUndefined(RabbitEvent rabbitEvent){
//         ConsumePaymentUndefinedData paymentUndefinedEvent = ConsumePaymentUndefinedData.fromJson(rabbitEvent.message.toString());
//         try{
//             OrderPaymentMethod nuevo = new OrderPaymentMethod();
//             nuevo.setOrderId(paymentUndefinedEvent.getOrderId());
//             orderPaymentMethodRepo.save(nuevo);
//         } catch (Exception e){
//             paymentMethodLogger.error(e.toString(), e);
//         }
//     }
// }