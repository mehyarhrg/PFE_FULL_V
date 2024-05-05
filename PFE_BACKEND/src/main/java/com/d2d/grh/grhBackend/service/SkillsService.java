package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.Skills;
import com.d2d.grh.grhBackend.models.CandidateSkillObject;
import com.d2d.grh.grhBackend.repository.CandidateRepository;
import com.d2d.grh.grhBackend.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SkillsService {

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Transactional
    public String addSkillsToCandidate(CandidateSkillObject candidateSkillObject){
        Candidate candidate = this.candidateRepository.findByUsername(candidateSkillObject.getCandidateUsername());
        System.out.println(candidate.getEmail());
        if (candidate.getSkills()==null){
            candidate.setSkills(candidateSkillObject.getSkills());
        }else{
            candidateSkillObject.getSkills().forEach(item->{
                if (this.skillsRepository.findByName(item.getName())!=null){
                    System.out.println(item.getName());
                    candidate.getSkills().add(this.skillsRepository.findByName(item.getName()));
                }else{
                    Skills newSkill = this.skillsRepository.save(item);
                    candidate.getSkills().add(newSkill);
                }

            });
        }

        return "skills added";
    }
}
