package com.manual.freelancer.application.controller;

import com.manual.freelancer.application.DTO.request.SuggestionRequest;
import com.manual.freelancer.application.DTO.response.SuggestionResponse;
import com.manual.freelancer.domain.service.impl.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping
    public ResponseEntity<SuggestionResponse> createSuggestion(@RequestBody SuggestionRequest suggestionRequest) {
        return ResponseEntity.ok(suggestionService.createSuggestion(suggestionRequest));
    }

    @GetMapping
    public ResponseEntity<List<SuggestionResponse>> getAllSuggestions() {
        return ResponseEntity.ok(suggestionService.getAllSuggestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuggestionResponse> getSuggestionById(@PathVariable UUID id) {
        return ResponseEntity.ok(suggestionService.getSuggestionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuggestionResponse> updateSuggestion(@PathVariable UUID id, @RequestBody SuggestionRequest suggestionRequest) {
        return ResponseEntity.ok(suggestionService.updateSuggestion(id, suggestionRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuggestion(@PathVariable UUID id) {
        suggestionService.deleteSuggestion(id);
        return ResponseEntity.noContent().build();
    }
}
