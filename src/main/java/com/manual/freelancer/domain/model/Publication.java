package com.manual.freelancer.domain.model;

import com.manual.freelancer.application.DTO.request.PublicationRequest;
import com.manual.freelancer.domain.enums.PublicationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "publications")
@Data
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private PublicationStatus status;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Suggestion> suggestions;

    public Publication(PublicationRequest request) {
        this.user = request.getUser();
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.createdAt = ZonedDateTime.now();
        this.status = PublicationStatus.OPEN;
    }

    public Publication() {}
}

