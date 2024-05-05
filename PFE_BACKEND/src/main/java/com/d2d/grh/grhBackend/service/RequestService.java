package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.converter.RequestConverter;
import com.d2d.grh.grhBackend.converter.UserConverter;
import com.d2d.grh.grhBackend.dto.RequestDTO;
import com.d2d.grh.grhBackend.dto.UserDto;
import com.d2d.grh.grhBackend.entity.Department;
import com.d2d.grh.grhBackend.entity.Request;
import com.d2d.grh.grhBackend.entity.Status;
import com.d2d.grh.grhBackend.entity.User;
import com.d2d.grh.grhBackend.exception.RequestNotFoundException;
import com.d2d.grh.grhBackend.repository.RequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserConverter userConverter;
    private final DepartmentService departmentService;
    private final RequestConverter requestConverter;

    @Autowired
    public RequestService(RequestRepository requestRepository, UserConverter userConverter, DepartmentService departmentService, RequestConverter requestConverter) {
        this.requestRepository = requestRepository;
        this.userConverter = userConverter;
        this.departmentService = departmentService;
        this.requestConverter = requestConverter;
    }

    public void addNewRequest(String request, String user, String department, String status) throws JsonProcessingException {
        Request request1 = new ObjectMapper().readValue(request, Request.class);
        UserDto user1 =  new ObjectMapper().readValue(user, UserDto.class);
        Department dep = new ObjectMapper().readValue(department, Department.class);
        Status stat = new ObjectMapper().readValue(status, Status.class);
        User requestUser = this.userConverter.customDtoEntity(user1, dep);
        request1.setUser(requestUser);
        request1.setStatus(stat);
        this.requestRepository.save(request1);
    }

    public List<RequestDTO> getAllRequests(){
        return this.requestConverter.entityToDto(this.requestRepository.findAll());
    }

    public void changeRequestStatus(Long requestId, Status status){
        Request request = this.requestRepository.findById(requestId).orElseThrow(()-> new RequestNotFoundException("No request match with this ID !!"));
        request.setStatus(status);
        this.requestRepository.save(request);
    }

    public void deleteRequest(Long requestId){
        this.requestRepository.deleteById(requestId);
    }

}
