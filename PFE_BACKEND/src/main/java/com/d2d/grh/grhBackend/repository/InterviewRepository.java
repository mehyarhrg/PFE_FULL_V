package com.d2d.grh.grhBackend.repository;

import com.d2d.grh.grhBackend.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview,Long > {
}
