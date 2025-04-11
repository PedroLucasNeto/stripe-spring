package com.manual.freelancer.domain.service.impl;

import com.manual.freelancer.application.DTO.response.DonationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manual.freelancer.domain.model.Donation;
import com.manual.freelancer.domain.repository.DonationRepository;
import com.manual.freelancer.domain.service.DonationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    DonationRepository donationRepository;

    @Override
    public void createDonation(String name, String email, Double amount, String currency) {
        donationRepository.save(new Donation(name, email, amount, currency));
        System.out.println("Creating donation for " + name + " with amount: " + amount);
    }

    @Override
    public Double countTotalDonations() {
        return donationRepository.findAll()
                .stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }

    @Override
    public List<DonationResponse> getAllDonations() {
        return donationRepository.findAll()
                .stream()
                .map(DonationResponse::new)
                .collect(Collectors.toList());
    }
    
}
