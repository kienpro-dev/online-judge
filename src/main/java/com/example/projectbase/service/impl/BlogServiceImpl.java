package com.example.projectbase.service.impl;

import com.example.projectbase.domain.dto.BlogDto;
import com.example.projectbase.domain.entity.Blog;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.repository.BlogRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.BlogService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    private final UserRepository userRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findByOrderByCreatedDateDesc();
    }

    @Override
    public Blog createBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setContent(blogDto.getContent());
        blog.setName(blogDto.getName());

        User u = userRepository.findUserById(blogDto.getUserId()).orElse(null);
        if(u != null) {
            blog.setUser(u);
        } else {
            blog.setUser(userRepository.findUserById(1L).orElse(null));
        }
        return blogRepository.save(blog);
    }
}
