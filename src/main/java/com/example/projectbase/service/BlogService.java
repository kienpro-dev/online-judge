package com.example.projectbase.service;

import com.example.projectbase.domain.dto.BlogDto;
import com.example.projectbase.domain.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();

    Blog createBlog(BlogDto blogDto);
}
