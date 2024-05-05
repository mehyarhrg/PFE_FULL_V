package com.d2d.grh.grhBackend.repository;

import com.d2d.grh.grhBackend.entity.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferStatusRepository extends JpaRepository<OfferStatus, Long> {
}
