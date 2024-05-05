package com.d2d.grh.grhBackend.dto;

import lombok.Data;

@Data
public class FeedBackDTO {
    private Integer feedBackId;
    private String feedBackText;
    private int candidate_id;
    private String feedBackType;
}
