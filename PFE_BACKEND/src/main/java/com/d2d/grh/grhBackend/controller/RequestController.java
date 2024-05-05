package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.converter.RequestConverter;
import com.d2d.grh.grhBackend.dto.RequestDTO;
import com.d2d.grh.grhBackend.entity.Request;
import com.d2d.grh.grhBackend.entity.Status;
import com.d2d.grh.grhBackend.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/newRequest")
    public void addNewRequest(@RequestParam("request") String request, @RequestParam("user") String user, @RequestParam("department") String department, @RequestParam("status") String status) throws JsonProcessingException {
        this.requestService.addNewRequest(request, user, department, status);
    }

    @GetMapping("/allRequests")
    public List<RequestDTO> getAllRequests(){
        return this.requestService.getAllRequests();
    }

    @DeleteMapping("/deleteRequest/{requestId}")
    public void deleteRequest(@PathVariable Long requestId){
        this.requestService.deleteRequest(requestId);
    }

    @PostMapping("/updateRequestStatus/{requestId}")
    public void updateRequestStatus(@PathVariable Long requestId, @RequestBody Status status){
        this.requestService.changeRequestStatus(requestId, status);
    }
}
