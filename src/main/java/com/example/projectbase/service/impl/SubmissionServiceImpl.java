package com.example.projectbase.service.impl;

import com.example.projectbase.domain.dto.SubmissionDto;
import com.example.projectbase.domain.entity.Contest;
import com.example.projectbase.domain.entity.Submission;
import com.example.projectbase.domain.mapper.SubmissionMapper;
import com.example.projectbase.repository.ContestRepository;
import com.example.projectbase.repository.ExerciseRepository;
import com.example.projectbase.repository.SubmissionRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.SubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;

    private final UserRepository userRepository;

    private final ExerciseRepository exerciseRepository;

    private final ContestRepository contestRepository;

    @Override
    public Page<Submission> getAllSubmissions(Pageable pageable) {
        return submissionRepository.findAll(pageable);
    }

    @Override
    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id).orElse(null);
    }

    @Override
    public Submission createSubmission(SubmissionDto submissionDto) {
        Submission submission = new Submission();
        submission.setStatus(submissionDto.getStatus());
        submission.setCode(submissionDto.getCode());
        submission.setCodeType(submissionDto.getCodeType());
        submission.setUser(userRepository.findUserById(submissionDto.getUserId()).orElse(null));
        submission.setExercise(exerciseRepository.findById(submissionDto.getExId()).orElse(null));
        Contest contest = contestRepository.findById(1L).orElse(null);
        if(submissionDto.getContestId() != null) {
            contest = contestRepository.findById(submissionDto.getContestId()).orElse(null);
        }
        submission.setContest(contest);
        return submissionRepository.save(submission);
    }
}
