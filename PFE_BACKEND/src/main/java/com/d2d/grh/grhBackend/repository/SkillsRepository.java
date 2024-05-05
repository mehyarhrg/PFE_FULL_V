package com.d2d.grh.grhBackend.repository;

import com.d2d.grh.grhBackend.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Integer> {

    Skills findByName(String SkillName);

}
