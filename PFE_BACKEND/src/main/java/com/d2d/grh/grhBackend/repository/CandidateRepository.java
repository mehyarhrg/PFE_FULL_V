package com.d2d.grh.grhBackend.repository;

import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    Candidate findByUsername(String username);
}
