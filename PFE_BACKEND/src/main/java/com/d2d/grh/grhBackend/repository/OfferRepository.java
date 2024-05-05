package com.d2d.grh.grhBackend.repository;

import com.d2d.grh.grhBackend.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Offer findOfferByOfferRef(String offerRef);
}
