package com.d2d.grh.grhBackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackModel {
    private String feedBackText;
    private int candidate_id;
    private String feedBackType;
}
