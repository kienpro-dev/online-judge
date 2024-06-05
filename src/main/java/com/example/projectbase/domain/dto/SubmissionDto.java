package com.example.projectbase.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubmissionDto {
    private String status;

    private String code;

    private String codeType;

    private Long userId;

    private Long exId;

    private Long contestId;
}
