package com.d2d.grh.grhBackend.repository;

import com.d2d.grh.grhBackend.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBachRepository extends JpaRepository<FeedBack, Integer> {
}
