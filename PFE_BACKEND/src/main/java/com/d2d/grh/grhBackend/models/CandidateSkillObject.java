package com.d2d.grh.grhBackend.models;

import com.d2d.grh.grhBackend.entity.Skills;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class CandidateSkillObject {

    private String candidateUsername;
    private Collection<Skills> Skills;
}
