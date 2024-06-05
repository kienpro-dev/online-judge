package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {
}
