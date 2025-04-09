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
// import java.util.Map;
// import com.stripe.param.checkout.SessionCreateParams;
// import com.stripe.Stripe;
// import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;

@RestController
@RequestMapping("/stripe")
public class StripeController {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Stripe service is running");
    }

    @PostMapping("/receive-donation")
    public ResponseEntity<String> receiveDonation(@RequestBody DonationRequest donation) {
        try {
            Product product = Product.create(ProductCreateParams.builder()
                    .setName("Custom Donation")
                    .build());

            Price price = Price.create(PriceCreateParams.builder()
                    .setUnitAmount(donation.getAmount().longValue() * 100) // amount in cents
                    .setCurrency("brl") // or "brl", etc.
                    .setProduct(product.getId())
                    .build());

            PaymentLink paymentLink = PaymentLink.create(PaymentLinkCreateParams.builder()
                    .addLineItem(PaymentLinkCreateParams.LineItem.builder()
                            .setPrice(price.getId())
                            .setQuantity(1L)
                            .build())
                    .build());

            return ResponseEntity.ok(paymentLink.getUrl());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating payment link");
        }
    }

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
                case "invoice.payment_succeeded":
                    System.out.println("Invoice payment succeeded.");
                    System.out.println("data: " + event.getData());
                    break;
                case "customer.subscription.deleted":
                    System.out.println("Subscription canceled.");
                    break;
                case "donation.succeeded":
                    // donationService.saveDonation();
                default:
                    break;
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Invalid webhook signature");
        }
    }
    

    //EXAMPLE OF SENDING RECEIPT
    // @PostMapping("/receive-donation")
    // public ResponseEntity<String> sendReceipt(@RequestBody DonationRequest donation) {
    
    //     PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
    //             .addLineItem(
    //                     PaymentLinkCreateParams.LineItem.builder()
    //                             .setPrice(donation.getAmount().toString())
    //                             .setQuantity(1L)
    //                             .build())
    //             .build();
    
    //     try {
    //         PaymentLink paymentLink = PaymentLink.create(params);
    //         return ResponseEntity.ok(paymentLink.getUrl());
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(500).body("Error creating payment link");
    //     }
    // }
}

// @PostMapping("/payment-session")
// public ResponseEntity<Map<String, String>> createCheckoutSession() {
// try {

// System.out.println("Creating checkout session..." + Stripe.apiKey);

// long price = 1000L; // $10.00
// String currency = "usd";

// SessionCreateParams params = SessionCreateParams.builder()
// .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
// .setSuccessUrl("http://localhost:5173/success")
// .setCancelUrl("http://localhost:5173/cancel")
// .addLineItem(
// SessionCreateParams.LineItem.builder()
// .setPriceData(
// SessionCreateParams.LineItem.PriceData.builder()
// .setCurrency(currency)
// .setUnitAmount(price)
// .setRecurring(
// SessionCreateParams.LineItem.PriceData.Recurring.builder()
// .setInterval(SessionCreateParams.LineItem.PriceData.Recurring.Interval.MONTH)
// .build())
// .setProductData(
// SessionCreateParams.LineItem.PriceData.ProductData.builder()
// .setName("Example Subscription")
// .build())
// .build())
// .setQuantity(1L)
// .build())
// .build();

// Session session = Session.create(params);

// return ResponseEntity.ok(Map.of("url", session.getUrl()));
// } catch (Exception e) {
// e.printStackTrace();
// return ResponseEntity.status(500).body(Map.of("error", "Error creating
// checkout session"));
// }
// }