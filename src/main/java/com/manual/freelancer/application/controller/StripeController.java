package com.manual.freelancer.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.manual.freelancer.application.DTO.request.DonationRequest;
import com.stripe.model.Event;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manual.freelancer.domain.model.Donation;
import com.manual.freelancer.domain.service.DonationService;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;

@RestController
@RequestMapping("/stripe")
public class StripeController {

    @Autowired
    private DonationService donationService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Stripe service is running");
    }

    @PostMapping("/receive-donation")
    public ResponseEntity<String> receiveDonation(@RequestBody DonationRequest donation) {
        try {
            SessionCreateParams params = SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("http://localhost:5173/success")
            .setCancelUrl("http://localhost:5173/cancel")
            .addLineItem(SessionCreateParams.LineItem.builder()
                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                    .setCurrency(donation.getCurrency())
                    .setUnitAmount(donation.getAmount().longValue() * 100)
                    .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName("Example Donation")
                            .build())
                    .build())
                .setQuantity(1L)
                .build())
            .setPaymentIntentData(SessionCreateParams.PaymentIntentData.builder()
                .putMetadata("userName", donation.getName())
                .putMetadata("userEmail", donation.getEmail())
                .putMetadata("currency", donation.getCurrency())
                .putMetadata("amount", String.valueOf(donation.getAmount()))
                .build())
            .build();
        
            Session session = Session.create(params);

            return ResponseEntity.ok(session.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating checkout session");
        }
    };

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {
        try {
            Event event = Webhook.constructEvent(payload, sigHeader,
                    "whsec_91cf711cb8c1fc2b39071ef27429a38e51343de0ee33738f4d034b0796490622");
            System.out.println("Received event: " + event.getType());

            switch (event.getType()) {
                case "checkout.session.completed":
                    System.out.println("Checkout session completed.");
                    break;
                case "payment_intent.succeeded":
                    JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
                    JsonObject data = jsonObject.getAsJsonObject("data");
                    JsonObject paymentIntent = data.getAsJsonObject("object");

                    JsonObject metadata = paymentIntent.getAsJsonObject("metadata");

                    String userName = metadata.get("userName").getAsString();
                    String userEmail = metadata.get("userEmail").getAsString();
                    Double amount = Double.valueOf(metadata.get("amount").getAsString());
                    String currency = metadata.get("currency").getAsString();

                    donationService.createDonation(userEmail, userName, amount, currency);
                    break;
                default:
                    break;
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Invalid webhook signature");
        }
    }
}
