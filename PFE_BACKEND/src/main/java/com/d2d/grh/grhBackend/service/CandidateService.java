package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.converter.CandidateConverter;
import com.d2d.grh.grhBackend.converter.OfferConverter;
import com.d2d.grh.grhBackend.converter.StatusConverter;
import com.d2d.grh.grhBackend.dto.CandidateDTO;
import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.Offer;
import com.d2d.grh.grhBackend.entity.Status;
import com.d2d.grh.grhBackend.models.Candidacy;
import com.d2d.grh.grhBackend.models.CandidateSkillObject;
import com.d2d.grh.grhBackend.repository.CandidateRepository;
import com.d2d.grh.grhBackend.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SkillsService skillsService;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OfferConverter offerConverter;

    @Autowired
    private CandidateConverter candidateConverter;

    @Autowired
    private StatusConverter statusConverter;


    public Candidate addNewCandidate(Candidate candidate){
        CandidateSkillObject candidateSkillObject = new CandidateSkillObject(candidate.getUsername(), candidate.getSkills());
        candidate.setPassword(this.bCryptPasswordEncoder.encode(candidate.getPassword()));
        candidate.setSkills(null);
        Candidate newCandidate = this.candidateRepository.save(candidate);
        this.skillsService.addSkillsToCandidate(candidateSkillObject);
        return newCandidate;
    }

    public List<Candidate> getAllCandidate(){
        return this.candidateRepository.findAll();
    }

    public Candidate getCandidateByUsername(String username){
        return this.candidateRepository.findByUsername(username);
    }

    public CandidateDTO getCandidatDTOByUsername(String username){
       return  this.candidateConverter.entityToDto(this.getCandidateByUsername(username));
    }
    @Transactional
    public String postuler(String candidateUsername, int offerId){
        System.out.println("enter");
        Candidate candidate = this.getCandidateByUsername(candidateUsername);
        if (offerId == 9999999){
            candidate.getOffers().add(new Offer());
            return "Posted from job platforms";
        }
        Offer offer = this.offerRepository.findById(offerId).orElseThrow(()-> new RuntimeException("no job found"));

        if (candidate == null || offer == null){
            return "candidate offer not exist";
        }else{
            candidate.getOffers().add(offer);
            System.out.println(candidate.getOffers().toString());
            return "Postuler avec succes !";
        }
    }
    @Transactional
    public String postuler(String candidateUsername, int offerId, String offerName){
        System.out.println("candidate name : "+candidateUsername);
        Candidate candidate = this.getCandidateByUsername(candidateUsername);
        if (offerId == 9999999){
            var off = new Offer();
            off.setOfferTitle(offerName);
            this.offerRepository.save(off);
            candidate.getOffers().add(off);
            candidate.getOffers().stream().findFirst().get().setOfferTitle(offerName);
            System.out.println("test job title");
            System.out.println(candidate.getOffers().stream().findFirst().get().getOfferTitle());
            return "Posted from job platforms";
        }
        Offer offer = this.offerRepository.findById(offerId).orElseThrow(()-> new RuntimeException("no job found"));

        if (candidate == null || offer == null){
            return "candidate offer not exist";
        }else{
            candidate.getOffers().add(offer);
            System.out.println(candidate.getOffers().toString());
            return "Postuler avec succes !";
        }
    }
    
    public List<Candidacy> getAllCandidacy(){
        List<Candidacy> candidacyList = new ArrayList<>();
        List<Candidate> candidateList = this.candidateRepository.findAll();
        for (Candidate candidate: candidateList) {
            if (candidate.getOffers() != null || !candidate.getOffers().isEmpty()){
                for(Offer offer: candidate.getOffers()){
                    candidacyList.add(new Candidacy(candidate.getCandidateId(),candidate.getFirstname(), candidate.getLastname(), candidate.getEmail(), candidate.getPhone(), candidate.getFeedBacks(), this.offerConverter.entityToDto(offer), candidate.getCvFilePath(), this.statusConverter.entityToDto(candidate.getStatus())));
                }
            }
        }
        return candidacyList;
    }

    public List<Candidacy> getMyCandidacy(int candidateId){
        List<Candidacy> candidacyList = new ArrayList<>();
        Candidate currentCandidate = this.candidateRepository.findById(candidateId).get();

        for (Offer myOffer: currentCandidate.getOffers()){
            candidacyList.add(
                    new Candidacy(
                            currentCandidate.getCandidateId(),
                            currentCandidate.getFirstname(),
                            currentCandidate.getLastname(),
                            currentCandidate.getEmail(),
                            currentCandidate.getPhone(),
                            currentCandidate.getFeedBacks(),
                            this.offerConverter.entityToDto(myOffer),
                            currentCandidate.getCvFilePath(),
                            this.statusConverter.entityToDto(currentCandidate.getStatus())
                    )
            );

        }
        return candidacyList;
    }


    @Transactional
    public String uploadCvFile(MultipartFile cvFile, String username) throws IOException {
        String folder = "C:\\cvFile\\";
        byte[] bytes = cvFile.getBytes();
        Path path = Paths.get(folder+cvFile.getOriginalFilename());
        Files.write(path, bytes);
        Candidate candidate = this.candidateRepository.findByUsername(username);
        candidate.setCvFilePath(folder+cvFile.getOriginalFilename());
        return "cv uploaded successfully";
    }

    public void updateCandidateStatus(Integer candidateId, Status status){
        // maintenir
        Candidate existingCandidate = this.candidateRepository.findById(candidateId).get();
        existingCandidate.setStatus(status);
        existingCandidate.setStatus_id(status.getStatusId().intValue());
        this.candidateRepository.save(existingCandidate);
    }


}
