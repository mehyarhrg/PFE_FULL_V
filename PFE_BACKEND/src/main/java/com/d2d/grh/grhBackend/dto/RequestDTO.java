package com.d2d.grh.grhBackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestDTO {

    private Long requestId;
    private String requestTitle;
    private Date deadLine;
    private String description;
    private boolean isUrgent;
    private String userFirstname;
    private String userLastname;
    private Long statusId;
    private String statusName;
    private String statusColor;
}
