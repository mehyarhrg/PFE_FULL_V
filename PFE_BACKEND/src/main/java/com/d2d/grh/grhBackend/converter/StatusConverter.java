package com.d2d.grh.grhBackend.converter;

import com.d2d.grh.grhBackend.dto.OfferDTO;
import com.d2d.grh.grhBackend.dto.StatusDTO;
import com.d2d.grh.grhBackend.entity.Offer;
import com.d2d.grh.grhBackend.entity.Status;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusConverter {

    public StatusDTO entityToDto(Status status){
        StatusDTO dto = new StatusDTO();
        dto.setStatusId(status.getStatusId());
        dto.setStatusName(status.getStatusName());
        dto.setStatusColor(status.getStatusColor());

        return dto;
    }

    public List<StatusDTO> entityToDto(List<Status> status){
        return status.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
}
