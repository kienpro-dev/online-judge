package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.ExerciseResponse;
import com.example.projectbase.domain.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findTop3ByOrderByCreatedDateDesc();
}
