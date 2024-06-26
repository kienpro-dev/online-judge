package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.ExerciseDto;
import com.example.projectbase.domain.dto.SubmissionDto;
import com.example.projectbase.domain.entity.Exercise;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.ExerciseMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.ExerciseRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.ExerciseService;
import com.example.projectbase.util.CompileUtil;
import com.example.projectbase.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    private final UserRepository userRepository;

    private final ExerciseMapper exerciseMapper;

    @Override
    public Page<Exercise> getAllExercise(Pageable pageable) {
        return exerciseRepository.findAll(pageable);
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    @Override
    public Exercise createExercise(ExerciseDto exerciseDto) throws IOException {
        User user = userRepository.findById(exerciseDto.getUserId()).orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID));
        Exercise exercise = exerciseMapper.toExercise(exerciseDto);
        exercise.setUser(user);
        String fileName = exerciseRepository.count() + 1 + "";
        String savePath = "static/uploads";
        exercise.setExUrl(FileUtil.saveFile(fileName, savePath, exerciseDto.getExFilePDf()));
        File pdfFile = FileUtil.getFileByPath("static/uploads/" + fileName + ".pdf");
        byte[] image = FileUtil.convertPdfToLongImage(pdfFile);
        FileUtil.saveByteToPng(image, fileName + ".png");
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> findRecentExercise() {
        return exerciseRepository.findTop3ByOrderByCreatedDateDesc();
    }

    @Override
    public SubmissionDto compileAndRunExercise(MultipartFile file, String code, Long id, Long userId, Long contestId) throws IOException {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID));
        String codeType;
        File tempFile = null;
        if(!file.isEmpty()) {
            tempFile = FileUtil.convertMultipartToFile(file);
            codeType = FileUtil.getFileNameExtension(tempFile);
        } else {
            codeType = code.contains("System.out.print") ? "java" : (code.contains("cout") ? "cpp" : (code.contains("scanf") ? "c" : "py"));
        }
//        String code = FileUtil.readDataFromFile(tempFile);

        String message = switch (codeType) {
            case "java" ->
                    CompileUtil.compileAndRunExercise(code, exercise.getTestInput(), exercise.getTestOutput());
            case "cpp", "c" ->
                    CompileUtil.compileAndRunCppExercise(code, exercise.getTestInput(), exercise.getTestOutput());
            case "py" ->
                    CompileUtil.compileAndRunPythonExercise(code, exercise.getTestInput(), exercise.getTestOutput());
            default -> "error";
        };

        return new SubmissionDto(message, code, codeType.toUpperCase(), userId, id, contestId);
    }

}
