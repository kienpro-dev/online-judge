package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
