package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.converter.InterviewConverter;
import com.d2d.grh.grhBackend.converter.UserConverter;

import com.d2d.grh.grhBackend.dto.InterviewDTO;
import com.d2d.grh.grhBackend.entity.Interview;
import com.d2d.grh.grhBackend.entity.Offer;
import com.d2d.grh.grhBackend.entity.User;
import com.d2d.grh.grhBackend.models.InterviewModel;
import com.d2d.grh.grhBackend.repository.InterviewRepository;
import com.d2d.grh.grhBackend.repository.OfferRepository;
import com.d2d.grh.grhBackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    private InterviewRepository interviewRepository;
    private UserRepository userRepository;
    private UserConverter userConverter;
    private CandidateService candidateService;
    private OfferRepository offerRepository;
    private InterviewConverter interviewConverter;
    private EmailService emailService;

    @Autowired
    public InterviewService(InterviewRepository interviewRepository, UserRepository userRepository, UserConverter userConverter, CandidateService candidateService, OfferRepository offerRepository, InterviewConverter interviewConverter, EmailService emailService) {
        this.interviewRepository = interviewRepository;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.candidateService = candidateService;
        this.offerRepository = offerRepository;
        this.interviewConverter = interviewConverter;
        this.emailService = emailService;
    }

    public void addNewInterview(InterviewModel interviewModel) {

        User interviewer = this.userRepository.findByUsername(interviewModel.getInterviewerName());
        Offer offer = this.offerRepository.findOfferByOfferRef(interviewModel.getSubject());

        this.emailService.sendMail(interviewModel.getCandidateEmail(),
                "Convocation Pour entretien",
                "Madame/Monsieur,\n" +
                        "Votre candidature au poste de ( "+ offer.getOfferTitle() + " ("+ offer.getOfferRef()+")" +" ) au sein de notre société a retenu toute notre attention et nous souhaiterions vous rencontrer. Nous vous proposons un entretien avec Mme/M. ("+ interviewer.getFirstname()+" "+interviewer.getLastname() + "), le " + interviewModel.getInterviewDate() +" à "+interviewModel.getInterviewHour()+ "\nMerci de vous joindre à ce lien Google Meet("+ interviewModel.getLienGoogleMeet()+")selon l'horaire prévu."+"\nNous vous prions de bien vouloir nous confirmer votre présence à ce rendez-vous par email/par téléphone au (numéro)\nDans l’attente de vous rencontrer");

        Interview interview = new Interview();
        interview.setInterviewDate(interviewModel.getInterviewDate());
        interview.setInterviewHour(interviewModel.getInterviewHour());
        interview.setOffer(offer);
        interview.setInterviewer(interviewer);
        interview.setCandidateName(interviewModel.getCandidateName());
        this.interviewRepository.save(interview);
    }

    public List<InterviewDTO> getAllInterviews() {
        return this.interviewConverter.entityToDto(this.interviewRepository.findAll());
    }
}
