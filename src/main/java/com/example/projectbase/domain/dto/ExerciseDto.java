package com.example.projectbase.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExerciseDto {
    private String name;

    private String category;

    private double score;

    private MultipartFile exFilePDf;

    private String testInput;

    private String testOutput;

    private Long userId;
}
