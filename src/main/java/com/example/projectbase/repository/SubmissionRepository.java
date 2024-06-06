package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Page<Submission> findByOrderByCreatedDateDesc(Pageable pageable);
}
