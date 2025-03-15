package com.manual.freelancer.domain.service;

import com.manual.freelancer.application.DTO.request.PublicationRequest;
import com.manual.freelancer.application.DTO.response.PublicationResponse;
import com.manual.freelancer.domain.enums.PublicationStatus;
import com.manual.freelancer.domain.model.Publication;
import com.manual.freelancer.domain.repository.IPublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PublicationService {

    @Autowired
    private IPublicationRepository publicationRepository;

    @Transactional
    public PublicationResponse createPublication(PublicationRequest request) {
        Publication publication = publicationRepository.save(new Publication(request));
        return new PublicationResponse(publication);
    }

    public List<PublicationResponse> getAllPublications() {
        return publicationRepository.findAll().stream()
                .map(PublicationResponse::new)
                .collect(Collectors.toList());
    }

    public PublicationResponse getPublicationById(UUID id) {
        return publicationRepository.findById(id)
                .map(PublicationResponse::new)
                .orElseThrow(() -> new RuntimeException("Publication not found"));
    }

    @Transactional
    public PublicationResponse updatePublication(UUID id, PublicationRequest request) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication not found"));

        publication.setTitle(request.getTitle());
        publication.setDescription(request.getDescription());

        publication = publicationRepository.save(publication);
        return new PublicationResponse(publication);
    }

    @Transactional
    public void deletePublication(UUID id) {
        if (!publicationRepository.existsById(id)) {
            throw new RuntimeException("Publication not found");
        }
        publicationRepository.deleteById(id);
    }

    @Transactional
    public PublicationResponse updatePublicationStatus(UUID id, PublicationStatus status) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication not found"));

        publication.setStatus(status);
        publication = publicationRepository.save(publication);

        return new PublicationResponse(publication);
    }
}
