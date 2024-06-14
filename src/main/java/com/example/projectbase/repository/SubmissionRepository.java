package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Page<Submission> findByOrderByCreatedDateDesc(Pageable pageable);

    Page<Submission> findByExerciseIdInOrderByCreatedDateDesc(List<Long> id, Pageable pageable);

    Page<Submission> findByUserIdInOrderByCreatedDateDesc(List<Long> userId, Pageable pageable);

}
