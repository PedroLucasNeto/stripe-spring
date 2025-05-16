package com.manual.freelancer.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manual.freelancer.application.DTO.request.SuggestionRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "suggestions")
@Data
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "suggested_price", nullable = false)
    private Double suggestedPrice;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at", nullable = true)
    private ZonedDateTime updatedAt;

    public Suggestion() {
    }

    public Suggestion(SuggestionRequest request) {
        this.user = request.getUser();
        this.suggestedPrice = request.getSuggestedPrice();
        this.comment = request.getComment();
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }
}

