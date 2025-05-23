package com.manual.freelancer.application.DTO.response;

import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.domain.model.User;
import lombok.Data;

import java.util.UUID;

@Data
public class SuggestionResponse {

    private User user;

    private UUID id;

    private Double suggestedPrice;

    private String comment;

    public SuggestionResponse(Suggestion suggestion) {
        this.user = suggestion.getUser();
        this.id = suggestion.getId();
        this.suggestedPrice = suggestion.getSuggestedPrice();
        this.comment = suggestion.getComment();
    }

    public SuggestionResponse(UUID id, Suggestion suggestion) {
        this.id = id;
        this.user = suggestion.getUser();
        this.id = suggestion.getId();
        this.suggestedPrice = suggestion.getSuggestedPrice();
        this.comment = suggestion.getComment();
    }
}
