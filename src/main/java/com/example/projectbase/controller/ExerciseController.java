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
    StringBuilder outputBuilder = new StringBuilder();

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
//            File tempFile = null;
            try {
//                tempFile = File.createTempFile("code", ".java"); // Or ".cpp" for C++
//                Files.write(tempFile.toPath(), file.getBytes());
                File tempFile = FileUtil.convertMultipartToFile(file);
                boolean isSuccess = compileAndExecuteCode(tempFile, "1");
                if (!isSuccess) {
                    model.addAttribute("result", "false");
                } else {
                    boolean outputMatches = compareOutput(outputBuilder.toString(), "Hello, World! 1");
                    if (outputMatches) {
                        model.addAttribute("result", outputBuilder.toString());
                    } else {
                        model.addAttribute("result", "false");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            finally {
//                if (tempFile != null) {
//                    tempFile.delete();
//                }
//            }
        }
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        model.addAttribute("exercise", exercise);
        return "problem_detail";
    }

    private boolean compileAndExecuteCode(File tempFile, String input) {
        try {
            // Compile the Java code
            ProcessBuilder compileProcess = new ProcessBuilder("java", tempFile.getAbsolutePath());
            compileProcess.redirectErrorStream(true);
            Process process = compileProcess.start();

            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();

            // Execute the compiled Java program
            // Capture the output
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }
            reader.close();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean compareOutput(String actualOutput, String expectedOutput) {
        // Trim whitespace and compare
        return actualOutput.trim().equals(expectedOutput.trim());
    }
}
