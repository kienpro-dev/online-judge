package com.example.projectbase.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponse {
    private Long id;

    private String name;

    private String category;

    private double score;

    private double percentAc;

    private int sumAc;
}
