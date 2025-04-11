package com.manual.freelancer.domain.model;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "donations")
@Data
public class Donation {

    public Donation(String name, String email, Double amount, String currency) {
        this.name = name;
        this.email = email;
        this.amount = amount;
        this.currency = currency;
        this.data = new Timestamp(System.currentTimeMillis());
    }

    public Donation() {
        this.data = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(name = "user_email", nullable = false, length = 200)
    private String email;
    
    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, length = 3)
    private String currency;
    
    @Column
    private Timestamp data;
}