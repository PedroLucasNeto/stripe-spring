package com.manual.freelancer.application.DTO.response;

import com.manual.freelancer.domain.model.Donation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationResponse {

    private String name;
    private String email;
    private Double amount;
    private String currency;
    private Timestamp data;

    public DonationResponse(Donation donation) {
        this.name = donation.getName();
        this.email = donation.getEmail();
        this.amount = donation.getAmount();
        this.data = donation.getData();
        this.currency = donation.getCurrency();
    }
}
