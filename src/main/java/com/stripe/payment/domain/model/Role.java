package com.stripe.payment.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    public Role() {
        this.createdAt = ZonedDateTime.now();
    }
}

