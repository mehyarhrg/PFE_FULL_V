package com.d2d.grh.grhBackend.converter;

import com.d2d.grh.grhBackend.dto.RequestDTO;
import com.d2d.grh.grhBackend.entity.Request;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestConverter {

    public RequestDTO entityToDto(Request request){
        RequestDTO dto = new RequestDTO();
        dto.setRequestId(request.getRequestId());
        dto.setRequestTitle(request.getRequestTitle());
        dto.setDeadLine(request.getDeadLine());
        dto.setDescription(request.getDescription());
        dto.setUrgent(request.isUrgent());
        dto.setUserFirstname(request.getUser().getFirstname());
        dto.setUserLastname(request.getUser().getLastname());
        dto.setStatusId(request.getStatus().getStatusId());
        dto.setStatusName(request.getStatus().getStatusName());
        dto.setStatusColor(request.getStatus().getStatusColor());
        return dto;
    }

    public List<RequestDTO> entityToDto(List<Request> requests){
        return requests.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
}
