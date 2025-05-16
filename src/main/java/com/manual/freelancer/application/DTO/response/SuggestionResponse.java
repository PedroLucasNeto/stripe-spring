package com.manual.freelancer.application.DTO.response;

import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.domain.model.User;
import lombok.Data;

@Data
public class SuggestionResponse {

    private User user;

    private Double suggestedPrice;

    private String comment;

    public SuggestionResponse(Suggestion suggestion) {
        this.user = suggestion.getUser();
        this.suggestedPrice = suggestion.getSuggestedPrice();
        this.comment = suggestion.getComment();
    }
}
