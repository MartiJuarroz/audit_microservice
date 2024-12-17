// package com.example.demo.rabbit;

// import com.example.demo.rabbit.dto.PublishPaymentDefinedData;
// import com.example.demo.utils.rabbit.DirectPublisher;
// import com.example.demo.utils.rabbit.RabbitEvent;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class PublishPaymentDefined {

//     @Autowired
//     DirectPublisher directPublisher;

//     public void publish(String exchange, String queue, PublishPaymentDefinedData send) {
//         RabbitEvent eventToSend = new RabbitEvent();
//         eventToSend.type = "payment-defined";
//         eventToSend.message = send;

//         directPublisher.publish(exchange, queue, eventToSend);
//     }
// }
