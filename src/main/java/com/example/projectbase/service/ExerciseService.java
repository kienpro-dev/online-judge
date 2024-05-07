package com.example.projectbase.service;

import com.example.projectbase.domain.dto.ExerciseDto;
import com.example.projectbase.domain.dto.response.ExerciseResponse;
import com.example.projectbase.domain.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExerciseService {
    Page<Exercise> getAllExercise(Pageable pageable);
}
