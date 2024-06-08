package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByOrderByCreatedDateDesc();
}
