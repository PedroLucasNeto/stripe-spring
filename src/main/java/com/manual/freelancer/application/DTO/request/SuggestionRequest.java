package com.manual.freelancer.application.DTO.request;

import com.manual.freelancer.domain.model.User;
import lombok.Data;

@Data
public class SuggestionRequest {

    private User user;

    private String publication;

    private double suggestedPrice;

    private String comment;
}
