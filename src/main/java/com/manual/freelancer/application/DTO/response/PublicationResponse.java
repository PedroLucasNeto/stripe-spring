package com.manual.freelancer.application.DTO.response;

import com.manual.freelancer.domain.model.Publication;
import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.domain.model.User;
import lombok.Data;

import java.util.List;

@Data
public class PublicationResponse {

    private User user;

    private String title;

    private String description;

    private List<Suggestion> suggestions;

    public PublicationResponse(Publication publication) {
        this.user = publication.getUser();
        this.title = publication.getTitle();
        this.description = publication.getDescription();
        this.suggestions = publication.getSuggestions();
    }
}
