package com.manual.freelancer.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.manual.freelancer.domain.model.Suggestion;
import com.manual.freelancer.domain.service.SuggestionService;

@RestController
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    public ResponseEntity<List<Suggestion>> getSuggestions(@PathVariable String publicationId) {
        var suggestions = suggestionService.getSuggestions(publicationId);
        
        return new ResponseEntity<List<Suggestion>>(suggestions, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}