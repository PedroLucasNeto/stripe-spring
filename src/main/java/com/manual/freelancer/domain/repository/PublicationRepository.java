package com.manual.freelancer.domain.repository;

import com.manual.freelancer.domain.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, UUID> {
}
