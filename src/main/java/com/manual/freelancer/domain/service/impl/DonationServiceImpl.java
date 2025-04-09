package com.manual.freelancer.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.manual.freelancer.domain.model.Donation;
import com.manual.freelancer.domain.repository.DonationRepository;
import com.manual.freelancer.domain.service.DonationService;

public class DonationServiceImpl implements DonationService {

    @Autowired
    DonationRepository donationRepository;

    @Override
    public void createDonation(String name, String email, Double amount) {
    
        donationRepository.save(new Donation(name, email, amount));
        System.out.println("Creating donation for " + name + " with amount: " + amount);
    }

    @Override
    public Double countTotalDonations() {
        return donationRepository.findAll()
                .stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }
    
}
