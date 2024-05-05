package com.d2d.grh.grhBackend.converter;

import com.d2d.grh.grhBackend.dto.CandidateDTO;
import com.d2d.grh.grhBackend.dto.FeedBackDTO;
import com.d2d.grh.grhBackend.dto.OfferDTO;
import com.d2d.grh.grhBackend.entity.FeedBack;
import com.d2d.grh.grhBackend.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedBackConverter {

    public FeedBackDTO entityToDto(FeedBack feedBack){
        FeedBackDTO dto = new FeedBackDTO();
        dto.setFeedBackId(feedBack.getFeedBackId());
        dto.setFeedBackText(feedBack.getFeedBackText());
        dto.setCandidate_id(feedBack.getCandidate_id());
        dto.setFeedBackType(feedBack.getFeedBackType());
        return dto;
    }

    public List<FeedBackDTO> entityToDto(List<FeedBack> feedBacks){
        return feedBacks.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }

}
