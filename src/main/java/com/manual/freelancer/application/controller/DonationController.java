package com.manual.freelancer.application.controller;

import com.manual.freelancer.application.DTO.response.DonationResponse;
import com.manual.freelancer.domain.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/count")
    public ResponseEntity<Double> countTotalDonations() {
        return ResponseEntity.ok(donationService.countTotalDonations());
    }

    @GetMapping
    public ResponseEntity<List<DonationResponse>> getAllDonations() {
        return ResponseEntity.ok(donationService.getAllDonations());
    }
}

