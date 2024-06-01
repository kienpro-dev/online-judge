package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.entity.Exercise;
import com.example.projectbase.service.ExerciseService;
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
public class ExerciseController extends BaseController{

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping(UrlConstant.Exercise.GET_EXERCISES)
    public String getPage(Model model,
                          @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Exercise> exercises = exerciseService.getAllExercise(PageRequest.of(page - 1, size));

        model.addAttribute("exercises", exercises);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", exercises.getTotalPages());
        model.addAttribute("currentUser", getCurrentUser());

        return "problems";
    }

    @GetMapping(UrlConstant.Exercise.GET_EXERCISE)
    public String getExerciseById(Model model, @PathVariable Long exerciseId) throws IOException {
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        File pdfFile = FileUtil.getFileByPath("static/uploads/" + exerciseId + ".pdf");
        byte[] image = FileUtil.convertPdfToLongImage(pdfFile);
        FileUtil.saveByteToPng(image, exerciseId + ".png");
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
