package com.swarnim.paymentservice.paymentgateway;
import com.razorpay.PaymentLink;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentGateway implements PaymentGateway {
    private RazorpayClient razorpayClient;

    RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException {
        //Call Razorpay  API to generate the payment link

//      source -   https://razorpay.com/docs/api/payments/payment-links/create-standard/


        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000); //Rs10.00
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1716741505);
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Payment for orderId "+ orderId.toString());
        JSONObject customer = new JSONObject();
        customer.put("name","+919000090000");
        customer.put("contact","Swarnim Indraguru");
        customer.put("email","swarnimindraguru1@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.linkedin.com/in/swarnim-indraguru/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}
