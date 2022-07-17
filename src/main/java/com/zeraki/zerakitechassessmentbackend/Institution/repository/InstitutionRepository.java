package com.zeraki.zerakitechassessmentbackend.Institution.repository;

import com.zeraki.zerakitechassessmentbackend.Institution.model.Institution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long> {
    // Extending the CrudRepository Interface, provides a powerful layer of Abstraction placed on top of a JPA Implementation.
    // The Abstraction Layer allows to access the persistence layer without having to provide our own DAO implementation from scratch.
    // By extending the CrudRepository Interface, Spring Data JPA will provide implementations for the repository's CRUD methods.
}
