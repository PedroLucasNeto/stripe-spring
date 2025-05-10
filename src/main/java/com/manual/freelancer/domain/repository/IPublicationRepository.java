package com.manual.freelancer.domain.repository;

import com.manual.freelancer.domain.model.Publication;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublicationRepository extends JpaRepository<Publication, UUID> {
}
