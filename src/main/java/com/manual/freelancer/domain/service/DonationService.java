package com.manual.freelancer.domain.service;

public interface DonationService {
    
    void createDonation(String name, String email, Double amount);

    Double countTotalDonations();
}
