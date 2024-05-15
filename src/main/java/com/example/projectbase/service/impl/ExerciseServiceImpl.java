package com.example.projectbase.service.impl;

import com.example.projectbase.domain.entity.Exercise;
import com.example.projectbase.repository.ExerciseRepository;
import com.example.projectbase.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Override
    public Page<Exercise> getAllExercise(Pageable pageable) {
        return exerciseRepository.findAll(pageable);
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }
}
