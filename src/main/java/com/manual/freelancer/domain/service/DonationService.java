package com.manual.freelancer.domain.service;

import com.manual.freelancer.application.DTO.response.DonationResponse;

import java.util.List;

public interface DonationService {
    
    void createDonation(String name, String email, Double amount, String currency);

    Double countTotalDonations();

    List<DonationResponse> getAllDonations();
}
