package com.manual.freelancer.application.controller;

import com.manual.freelancer.application.DTO.request.PublicationRequest;
import com.manual.freelancer.application.DTO.response.PublicationResponse;
import com.manual.freelancer.domain.enums.PublicationStatus;
import com.manual.freelancer.domain.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping
    public ResponseEntity<PublicationResponse> createPublication(@RequestBody PublicationRequest publicationRequest) {
        return ResponseEntity.ok(publicationService.createPublication(publicationRequest));
    }

    @GetMapping
    public ResponseEntity<List<PublicationResponse>> getAllPublications() {
        return ResponseEntity.ok(publicationService.getAllPublications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationResponse> getPublicationById(@PathVariable UUID id) {
        return ResponseEntity.ok(publicationService.getPublicationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationResponse> updatePublication(@PathVariable UUID id, PublicationRequest publicationRequest) {
        return ResponseEntity.ok(publicationService.updatePublication(id, publicationRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable UUID id) {
        publicationService.deletePublication(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<PublicationResponse> updatePublicationStatus(@PathVariable UUID id, @PathVariable PublicationStatus status) {
        return ResponseEntity.ok(publicationService.updatePublicationStatus(id, status));
    }
}

