package com.manual.freelancer.application.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest {

    private String name;
    private String email;
    private Double amount;
    private String currency;
}
