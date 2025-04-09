package com.manual.freelancer.domain.beans;

import org.springframework.context.annotation.Configuration;

import com.stripe.Stripe;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class StripeConfig {
    
    @Value("${stripe.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }
}
