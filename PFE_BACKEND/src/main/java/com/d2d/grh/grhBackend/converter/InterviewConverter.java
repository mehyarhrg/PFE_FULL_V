package com.d2d.grh.grhBackend.converter;

import com.d2d.grh.grhBackend.dto.InterviewDTO;
import com.d2d.grh.grhBackend.entity.Interview;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InterviewConverter {

    public InterviewDTO entityToDto(Interview interview){
        InterviewDTO dto = new InterviewDTO();
        dto.setInterviewer(interview.getInterviewer().getFirstname()+" " +interview.getInterviewer().getLastname()) ;
        dto.setInterviewDate(interview.getInterviewDate());
        dto.setInterviewHour(interview.getInterviewHour());
        dto.setLienGoogleMeet(interview.getLienGoogleMeet());
        dto.setOfferName(
                interview.getOffer().getOfferRef()
                        + " - "+
                        interview.getOffer().getOfferTitle()
                        +" ("+ interview.getCandidateName() +")"
        );

        return dto;
    }

    public List<InterviewDTO> entityToDto(List<Interview> interviews){
        return interviews.stream().map(i->this.entityToDto(i)).collect(Collectors.toList());
    }

}
