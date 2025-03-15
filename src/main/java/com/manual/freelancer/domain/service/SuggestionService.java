package com.manual.freelancer.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.infrastructure.persitence.SuggestionRepository;

@Service
public class SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<Suggestion> getSuggestions(UUID publicationId) {
        // var publication = publicationRepository.exists(publicationId);
        //
        // if (publication == null) {
        //     throw new Exception("Publication not found");
        // }
    
        return suggestionRepository.findAllByPublicationId(publicationId);
    }
}
