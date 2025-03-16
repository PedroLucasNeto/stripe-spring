package com.manual.freelancer.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.infrastructure.persitence.SuggestionRepository;

public class SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<Suggestion> getSuggestions(String publicationId) {
        // var publication = publicationRepository.exists(publicationId);
        //
        // if (publication == null) {
        //     throw new Exception("Publication not found");
        // }
    
        return suggestionRepository.getSuggestions(publicationId);
    }
}
