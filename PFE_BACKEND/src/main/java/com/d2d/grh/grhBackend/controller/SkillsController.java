package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.models.CandidateSkillObject;
import com.d2d.grh.grhBackend.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @PostMapping("/addSkillToCandidate")
    public void addSkillsToCandidate(@RequestBody CandidateSkillObject candidateSkillObject){
        this.skillsService.addSkillsToCandidate(candidateSkillObject);
    }
}
