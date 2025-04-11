package com.manual.freelancer.domain.service;

public interface StripeService {

    String createPaymentIntent(String amount, String currency);

}
