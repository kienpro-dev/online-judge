package com.example.projectbase.service;

import com.example.projectbase.domain.dto.ExerciseDto;
import com.example.projectbase.domain.dto.SubmissionDto;
import com.example.projectbase.domain.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    Page<Exercise> getAllExercise(Pageable pageable);

    Exercise getExerciseById(Long id);

    Exercise createExercise(ExerciseDto exerciseDto) throws IOException;

    List<Exercise> findRecentExercise();

    SubmissionDto compileAndRunExercise(MultipartFile file, String code, Long id, Long userId, Long contestId) throws IOException;
}
