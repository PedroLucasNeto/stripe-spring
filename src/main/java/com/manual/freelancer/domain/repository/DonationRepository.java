package com.manual.freelancer.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manual.freelancer.domain.model.Donation;


@Repository
public interface DonationRepository extends JpaRepository<Donation, UUID> {
    
}
