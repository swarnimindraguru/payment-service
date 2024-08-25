package com.swarnim.paymentservice.controllers;

import com.swarnim.paymentservice.dtos.InitiatePaymentRequestDto;
import com.swarnim.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto){
        try {
            return paymentService.initiatePayment(
                    requestDto.getAmount(),
                    requestDto.getOrderId()
            );
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/sample")
    public String sampleAPI() {
        return "Hello from Swarnim";
    }
}
