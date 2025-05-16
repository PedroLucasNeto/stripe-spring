package com.manual.freelancer.application.DTO.request;

import com.manual.freelancer.domain.model.User;
import lombok.Data;

import java.util.UUID;

@Data
public class SuggestionRequest {

    private User user;

    private UUID publication;

    private double suggestedPrice;

    private String comment;
}
