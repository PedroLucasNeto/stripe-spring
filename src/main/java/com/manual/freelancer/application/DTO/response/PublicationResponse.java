package com.manual.freelancer.application.DTO.response;

import com.manual.freelancer.domain.model.Publication;
import com.manual.freelancer.domain.model.User;
import lombok.Data;

@Data
public class PublicationResponse {

    private User user;

    private String title;

    private String description;

    public PublicationResponse(Publication publication) {
        this.user = publication.getUser();
        this.title = publication.getTitle();
        this.description = publication.getDescription();
    }
}
