package com.example.projectbase.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Submission {
    private String status;

    private String code;

    private String userId;

    private Long exId;
}
