package com.manual.freelancer.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manual.freelancer.domain.model.Suggestion;

public interface ISuggestionRepository extends JpaRepository<Suggestion, UUID>  {
    List<Suggestion> findAllByPublicationId(UUID publicationId);
}
