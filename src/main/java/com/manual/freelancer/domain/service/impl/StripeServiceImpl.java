package com.manual.freelancer.domain.service.impl;

import com.manual.freelancer.domain.service.StripeService;

import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {

    @Override
    public String createPaymentIntent(String amount, String currency) {
        return null;
    }
    
}
