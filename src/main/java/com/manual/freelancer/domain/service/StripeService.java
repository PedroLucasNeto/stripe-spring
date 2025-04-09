package com.manual.freelancer.domain.service;

import org.springframework.stereotype.Service;


@Service
public interface StripeService {

    String createPaymentIntent(String amount, String currency);
}
