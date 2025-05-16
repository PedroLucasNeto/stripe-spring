package com.manual.freelancer.domain.service.impl;

import com.manual.freelancer.application.DTO.request.SuggestionRequest;
import com.manual.freelancer.application.DTO.response.SuggestionResponse;
import com.manual.freelancer.domain.model.Publication;
import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.domain.repository.PublicationRepository;
import com.manual.freelancer.domain.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    public SuggestionService(SuggestionRepository suggestionRepository, PublicationRepository publicationRepository) {
        this.suggestionRepository = suggestionRepository;
        this.publicationRepository = publicationRepository;
    }

    @Transactional
    public SuggestionResponse createSuggestion(SuggestionRequest request) {
        Optional<Publication> publication = publicationRepository.findById(request.getPublication());

        Suggestion suggestion = new Suggestion(request);
        suggestion.setPublication(publication.orElse(null));

        Suggestion suggestionSave = this.suggestionRepository.save(suggestion);

        return new SuggestionResponse(suggestionSave);
    }

    public List<SuggestionResponse> getAllSuggestions() {
        return suggestionRepository.findAll().stream()
                .map(SuggestionResponse::new)
                .collect(Collectors.toList());
    }

    public SuggestionResponse getSuggestionById(UUID id) {
        return suggestionRepository.findById(id)
                .map(SuggestionResponse::new)
                .orElseThrow(() -> new RuntimeException("Suggestion not found"));
    }

    @Transactional
    public SuggestionResponse updateSuggestion(UUID id, SuggestionRequest request) {
        Suggestion suggestion = suggestionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suggestion not found"));

        suggestion.setUser(request.getUser());
        suggestion.setUser(request.getUser());
        suggestion.setUser(request.getUser());

        suggestion = suggestionRepository.save(suggestion);
        return new SuggestionResponse(suggestion);
    }

    @Transactional
    public void deleteSuggestion(UUID id) {
        if (!suggestionRepository.existsById(id)) {
            throw new RuntimeException("Suggestion not found");
        }
        suggestionRepository.deleteById(id);
    }
}
