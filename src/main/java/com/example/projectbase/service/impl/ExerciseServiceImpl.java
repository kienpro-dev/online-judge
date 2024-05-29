package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.ExerciseDto;
import com.example.projectbase.domain.entity.Exercise;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.ExerciseMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.ExerciseRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.ExerciseService;
import com.example.projectbase.util.CompileUtil;
import com.example.projectbase.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@RequiredArgsConstructor
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
        String savePath = "static/pdf";
        exercise.setExUrl(FileUtil.saveFile(fileName, savePath, exerciseDto.getExFilePDf()));
        return exerciseRepository.save(exercise);
    }

    @Override
    public String compileAndRunExercise(MultipartFile file, Long id) throws IOException {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID));
        File tempFile = FileUtil.convertMultipartToFile(file);
        String message = CompileUtil.compileAndRunExercise(tempFile, exercise.getTestInput(), exercise.getTestOutput());
        if (message.equals("success")) {

        } else if (message.equals("wrong")) {

        } else if (message.equals("failed")) {

        }

        return message;
    }

}
