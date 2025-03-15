package com.manual.freelancer.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manual.freelancer.domain.model.Suggestion;

@Repository
public interface ISuggestionRepository extends JpaRepository<Suggestion, UUID>  {
    public List<Suggestion> getSuggestions(String publicationId);
}
