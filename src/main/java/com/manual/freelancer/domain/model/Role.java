package com.manual.freelancer.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    public Role() {
        this.createdAt = ZonedDateTime.now();
    }
}

