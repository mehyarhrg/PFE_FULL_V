package com.d2d.grh.grhBackend.repository;

import com.d2d.grh.grhBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     User findByUsername(String username);

}
