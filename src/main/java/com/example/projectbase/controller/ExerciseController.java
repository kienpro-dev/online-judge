package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.entity.Exercise;
import com.example.projectbase.security.CurrentUser;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.ExerciseService;
import com.example.projectbase.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@UiV1
public class ExerciseController {


    private final ExerciseService exerciseService;

    @GetMapping(UrlConstant.Exercise.GET_EXERCISES)
    public String getPage(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size,
                          @CurrentUser UserPrincipal userPrincipal) {
        Page<Exercise> exercises = exerciseService.getAllExercise(PageRequest.of(page - 1, size));

        model.addAttribute("exercises", exercises);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", exercises.getTotalPages());
        model.addAttribute("currentUser", userPrincipal);

        return "problems";
    }

    @GetMapping(UrlConstant.Exercise.GET_EXERCISE)
    public String getExerciseById(Model model, @PathVariable Long exerciseId) {
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        model.addAttribute("exercise", exercise);
        return "problem_detail";
    }

    @PostMapping(UrlConstant.Exercise.SUBMIT_CODE)
    public String submitCode(Model model, @PathVariable Long exerciseId, @RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        if (!file.isEmpty()) {
            String message = exerciseService.compileAndRunExercise(file, exerciseId);
            if(message.equals("success")) {

            } else if (message.equals("wrong")) {

            } else if (message.equals("failed")) {

            }
        } else {
            model.addAttribute("error", "File chưa được chọn");
        }
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        model.addAttribute("exercise", exercise);
        return "problem_detail";
    }
}
