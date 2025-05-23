package com.manual.freelancer.domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
public class PublicationRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PublicationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPublication(UUID id, UUID userId, String title, String description, int status, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        String sql = "INSERT INTO public.publications (id, user_id, title, description, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                id,
                userId,
                title,
                description,
                status,
                Timestamp.from(createdAt.toInstant()),
                updatedAt != null ? Timestamp.from(updatedAt.toInstant()) : null
        );
    }
}
