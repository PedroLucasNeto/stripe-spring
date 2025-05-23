package com.manual.freelancer.domain.service.impl;

import com.manual.freelancer.application.DTO.request.SuggestionRequest;
import com.manual.freelancer.application.DTO.response.SuggestionResponse;
import com.manual.freelancer.domain.model.Publication;
import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.domain.repository.PublicationRepository;
import com.manual.freelancer.domain.repository.SuggestionRepository;
import com.manual.freelancer.domain.repository.impl.SuggestionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
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

    @Autowired
    private SuggestionRepositoryImpl suggestionRepositoryImpl;

    public SuggestionService(
            SuggestionRepository suggestionRepository,
            SuggestionRepositoryImpl suggestionRepositoryImpl,
            PublicationRepository publicationRepository
    ) {
        this.suggestionRepository = suggestionRepository;
        this.suggestionRepositoryImpl = suggestionRepositoryImpl;
        this.publicationRepository = publicationRepository;
    }

    @Transactional
    public SuggestionResponse createSuggestion(SuggestionRequest request) {
        Optional<Publication> publication = publicationRepository.findById(request.getPublication());

        if (publication.isEmpty()) {
            throw new IllegalArgumentException("Publicação não encontrada: " + request.getPublication());
        }

        UUID id = UUID.randomUUID();

        suggestionRepositoryImpl.insertSuggestion(
                id,
                publication.get().getId(),
                request.getUser().getId(),
                request.getSuggestedPrice(),
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                request.getComment()
        );
        return new SuggestionResponse(id, new Suggestion(request));
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
