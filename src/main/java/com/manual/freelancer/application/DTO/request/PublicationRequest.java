package com.manual.freelancer.application.DTO.request;

import com.manual.freelancer.domain.model.User;
import lombok.Data;

@Data
public class PublicationRequest {

    private User user;

    private String title;

    private String description;

}
