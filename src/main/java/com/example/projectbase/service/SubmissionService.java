package com.example.projectbase.service;

import com.example.projectbase.domain.dto.SubmissionDto;
import com.example.projectbase.domain.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubmissionService {
    Page<Submission> getAllSubmissions(Pageable pageable);

    Submission getSubmissionById(Long id);

    Submission createSubmission(SubmissionDto submissionDto);
}
