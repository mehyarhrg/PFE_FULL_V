package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.converter.FeedBackConverter;
import com.d2d.grh.grhBackend.dto.FeedBackDTO;
import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.FeedBack;
import com.d2d.grh.grhBackend.models.FeedBackModel;
import com.d2d.grh.grhBackend.repository.CandidateRepository;
import com.d2d.grh.grhBackend.repository.FeedBachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedBackService {

    @Autowired
    private FeedBachRepository feedBachRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private FeedBackConverter feedBackConverter;

    public void addNewFeedBack(FeedBackModel feedBackModel){
        Candidate candidate = this.candidateRepository.findById(feedBackModel.getCandidate_id()).get();
        this.feedBachRepository.save(new FeedBack(null, feedBackModel.getFeedBackText(), feedBackModel.getCandidate_id(),feedBackModel.getFeedBackType(), candidate));
        //this.feedBachRepository.save(feedBack);
    }

    public List<FeedBackDTO> getCandidateFeedBack(Integer candidateId){
       return this.feedBackConverter.entityToDto(this.feedBachRepository.findAll().stream().filter(feedBack -> feedBack.getCandidate_id() == candidateId).collect(Collectors.toList()));
    }
}
