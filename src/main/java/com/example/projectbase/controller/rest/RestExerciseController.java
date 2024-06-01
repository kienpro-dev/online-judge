package com.example.projectbase.controller.rest;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.ExerciseDto;
import com.example.projectbase.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestApiV1
public class RestExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @Operation(summary = "API get exercise by ID")
    @GetMapping(UrlConstant.Exercise.GET_EXERCISE)
    public ResponseEntity<?> getExercise(@PathVariable Long exerciseId) {
        return VsResponseUtil.success(exerciseService.getExerciseById(exerciseId));
    }

    @Operation(summary = "API get exercises")
    @GetMapping(UrlConstant.Exercise.GET_EXERCISES)
    public ResponseEntity<?> getExercises(@RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size) {
        return VsResponseUtil.success(exerciseService.getAllExercise(PageRequest.of(page - 1, size)));
    }

    @Operation(summary = "API create exercises")
    @PostMapping(UrlConstant.Exercise.CREATE_EXERCISES)
    public ResponseEntity<?> createExercises(@ModelAttribute ExerciseDto exerciseDto) throws IOException {
        return VsResponseUtil.success(exerciseService.createExercise(exerciseDto));
    }
}
