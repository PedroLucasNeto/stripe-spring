package com.manual.freelancer.domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
public class SuggestionRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SuggestionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertSuggestion(UUID id, UUID publicationId, UUID userId, double suggestedPrice, ZonedDateTime createdAt, ZonedDateTime updatedAt, String comment) {
        String sql = "INSERT INTO public.suggestions (id, publication_id, user_id, suggested_price, created_at, updated_at, comment) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                id,
                publicationId,
                userId,
                suggestedPrice,
                Timestamp.from(createdAt.toInstant()),
                updatedAt != null ? Timestamp.from(updatedAt.toInstant()) : null,
                comment
        );
    }
}
