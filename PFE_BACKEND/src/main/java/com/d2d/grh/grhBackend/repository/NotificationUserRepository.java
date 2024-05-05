package com.d2d.grh.grhBackend.repository;

import com.d2d.grh.grhBackend.entity.NotificationUser;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationUserRepository extends JpaRepository<NotificationUser, Integer> {
}
