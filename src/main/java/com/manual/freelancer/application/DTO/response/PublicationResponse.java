package com.manual.freelancer.application.DTO.response;

import com.manual.freelancer.domain.model.Publication;
import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.domain.model.User;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class PublicationResponse {

    private User user;

    private String title;

    private UUID id;

    private String description;

    private List<SuggestionResponse> suggestions;

    public PublicationResponse(Publication publication) {
        this.user = publication.getUser();
        this.id = publication.getId();
        this.title = publication.getTitle();
        this.description = publication.getDescription();
        if (publication.getSuggestions() != null) {
            this.suggestions = publication.getSuggestions().stream()
                    .map(SuggestionResponse::new)
                    .collect(Collectors.toList());
        }
    }
}
