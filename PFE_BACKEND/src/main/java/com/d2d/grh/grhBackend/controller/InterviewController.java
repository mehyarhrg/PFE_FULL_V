package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.dto.InterviewDTO;
import com.d2d.grh.grhBackend.entity.Interview;
import com.d2d.grh.grhBackend.models.InterviewModel;
import com.d2d.grh.grhBackend.service.InterviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InterviewController {

    private InterviewService interviewService;

    @Autowired
    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/newInterview")
    public void addNewInterview(@RequestBody InterviewModel interviewModel) {
        this.interviewService.addNewInterview(interviewModel);
    }

    @GetMapping("/allInterviews")
    public List<InterviewDTO> getAllInterviews(){
        return this.interviewService.getAllInterviews();
    }
}
