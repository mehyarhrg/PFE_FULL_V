package com.d2d.grh.grhBackend.converter;

import com.d2d.grh.grhBackend.dto.CandidateDTO;
import com.d2d.grh.grhBackend.dto.FeedBackDTO;
import com.d2d.grh.grhBackend.dto.OfferDTO;
import com.d2d.grh.grhBackend.dto.StatusDTO;
import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.Offer;
import com.d2d.grh.grhBackend.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateConverter {

    @Autowired
    private StatusConverter statusConverter;

    @Autowired
    private FeedBackConverter feedBackConverter;

    @Autowired
    private OfferConverter offerConverter;


    public CandidateDTO entityToDto(Candidate candidate){
        CandidateDTO dto = new CandidateDTO();
        dto.setCandidateId(candidate.getCandidateId());
        dto.setFirstname(candidate.getFirstname());
        dto.setLastname(candidate.getLastname());
        dto.setEmail(candidate.getEmail());
        dto.setMessage(candidate.getMessage());
        dto.setPhone(candidate.getPhone());
        dto.setCvFilePath(candidate.getCvFilePath());
        dto.setStatus_id(candidate.getStatus_id());
        dto.setStatusDTO(this.statusConverter.entityToDto(candidate.getStatus()));
        if (candidate.getOffers() != null){
            dto.setOffers(this.offerConverter.entityToDto(candidate.getOffers()));
        }
        if (candidate.getFeedBacks()!=null){
            dto.setFeedBackDTO(this.feedBackConverter.entityToDto(candidate.getFeedBacks()));
        }else {
            dto.setFeedBackDTO(new ArrayList<FeedBackDTO>());
        }
        dto.setSkills(candidate.getSkills());


        return dto;
    }

    public List<CandidateDTO> entityToDto(List<Candidate> candidate){
        return candidate.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
}
