package com.example.projectbase.service;

import com.example.projectbase.domain.dto.SubmissionDto;
import com.example.projectbase.domain.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubmissionService {
    Page<Submission> getAllSubmissions(Pageable pageable);

    Submission getSubmissionById(Long id);

    Page<Submission> getAllSubmissionsByExerciseId(Long id, Pageable pageable);

    Submission createSubmission(SubmissionDto submissionDto);
}
