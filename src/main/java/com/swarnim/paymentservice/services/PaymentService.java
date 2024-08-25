package com.swarnim.paymentservice.services;

import com.razorpay.RazorpayException;
import com.swarnim.paymentservice.paymentgateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;

    PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    public String initiatePayment(Long amount, Long orderId) throws RazorpayException {
        //Make calls to PaymentGateway to generate the payment link
        return paymentGateway.generatePaymentLink(orderId, amount);
    }
}
