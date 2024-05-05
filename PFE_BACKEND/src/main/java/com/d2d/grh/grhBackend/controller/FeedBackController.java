package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.dto.FeedBackDTO;
import com.d2d.grh.grhBackend.entity.FeedBack;
import com.d2d.grh.grhBackend.models.FeedBackModel;
import com.d2d.grh.grhBackend.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    @PostMapping("/newFeedback")
    public void saveFeedBack(@RequestBody FeedBackModel feedBack){
        this.feedBackService.addNewFeedBack(feedBack);
    }

    @GetMapping("/candidateFeedBack/{candidateId}")
    public List<FeedBackDTO > getCandidateFeedBack(@PathVariable Integer candidateId){
        return this.feedBackService.getCandidateFeedBack(candidateId);
    }
}
