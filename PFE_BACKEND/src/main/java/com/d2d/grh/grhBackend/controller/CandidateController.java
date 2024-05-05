package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.converter.CandidateConverter;
import com.d2d.grh.grhBackend.dto.CandidateDTO;
import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.Status;
import com.d2d.grh.grhBackend.models.Candidacy;
import com.d2d.grh.grhBackend.models.CandidateOfferObject2;
import com.d2d.grh.grhBackend.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateConverter candidateConverter;

    @PostMapping("/addNewCandidate")
    public CandidateDTO addNewCandidate(@RequestBody Candidate candidate){
        return this.candidateConverter.entityToDto(this.candidateService.addNewCandidate(candidate));
    }

    @GetMapping("/candidates")
    public List<CandidateDTO> getAllCandidate(){
        return this.candidateConverter.entityToDto(this.candidateService.getAllCandidate());
    }

    @GetMapping("/candidate/{username}")
    public CandidateDTO getCandidateByUsername(@PathVariable String username){
        return this.candidateConverter.entityToDto(this.candidateService.getCandidateByUsername(username));
    }

    @PostMapping("/postuler")
    public String postuler(@RequestBody CandidateOfferObject2 candidateOfferObject){
        System.out.println("im here");
        return this.candidateService.postuler(candidateOfferObject.getCandidateUsername(), candidateOfferObject.getOfferId());
    }
    @PostMapping("/postulerFromPlateform")
    public String postulerFromPlateform(@RequestBody CandidateOfferObject2 candidateOfferObject){
        System.out.println("im plateform");
        return this.candidateService.postuler(candidateOfferObject.getCandidateUsername(), candidateOfferObject.getOfferId(), candidateOfferObject.getOfferName());
    }

    @GetMapping("/allCandidacy")
    public List<Candidacy> getAllCandidacy(){
        return this.candidateService.getAllCandidacy();
    }

    @GetMapping("/myCandidacy/{candidateId}")
    public List<Candidacy> getMyCandidacy(@PathVariable int candidateId){
        return this.candidateService.getMyCandidacy(candidateId);
    }

    @PostMapping("/uploadCv")
    public String uploadCv(@RequestParam("cvFile") MultipartFile cvFile, @RequestParam("username") String username) throws IOException {
        return this.candidateService.uploadCvFile(cvFile, username);
    }

    @PostMapping("/updateCandidateStatus/{candidateId}")
    public void updateCandidateStatus(@PathVariable Integer candidateId, @RequestBody Status status){
        this.candidateService.updateCandidateStatus(candidateId, status);
    }

}
