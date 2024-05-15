package com.example.projectbase.service;

import com.example.projectbase.domain.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ExerciseService {
    Page<Exercise> getAllExercise(Pageable pageable);

    Exercise getExerciseById(Long id);
}
