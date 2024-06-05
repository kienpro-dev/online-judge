package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.SubmissionDto;
import com.example.projectbase.domain.entity.Exercise;
import com.example.projectbase.domain.entity.Submission;
import com.example.projectbase.service.ExerciseService;
import com.example.projectbase.service.SubmissionService;
import com.example.projectbase.util.FileUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@UiV1
public class ExerciseController extends BaseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SubmissionService submissionService;

    @GetMapping(UrlConstant.Exercise.GET_EXERCISES)
    public String getPage(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Exercise> exercises = exerciseService.getAllExercise(PageRequest.of(page - 1, size));

        model.addAttribute("exercises", exercises);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", exercises.getTotalPages());

        return "problems";
    }

    @GetMapping(UrlConstant.Exercise.GET_EXERCISE)
    public String getExerciseById(Model model, @PathVariable Long exerciseId) throws IOException {
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        model.addAttribute("exercise", exercise);
        return "problem_detail";
    }

    @PostMapping(UrlConstant.Exercise.SUBMIT_CODE)
    public String submitCode(Model model, @PathVariable Long exerciseId, @RequestParam("file") MultipartFile file, @RequestParam(required = false) Long contestId) throws IOException, InterruptedException {
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        model.addAttribute("exercise", exercise);
        if (getCurrentUser() == null) {
            model.addAttribute("auth", "Bạn cần đăng nhập để nộp bài");
            return "problem_detail";
        }
        if (!file.isEmpty()) {
            SubmissionDto submissionDto = exerciseService.compileAndRunExercise(file, exerciseId, getCurrentUser().getId(), contestId);
            Submission submission = submissionService.createSubmission(submissionDto);
            return "redirect:/ui/v1/submission";

        } else {
            model.addAttribute("error", "File chưa được chọn");
        }
        return "problem_detail";
    }
}
