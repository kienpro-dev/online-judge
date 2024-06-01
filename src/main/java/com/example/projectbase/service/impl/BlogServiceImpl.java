package com.example.projectbase.service.impl;

import com.example.projectbase.domain.entity.Blog;
import com.example.projectbase.repository.BlogRepository;
import com.example.projectbase.service.BlogService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}
