package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.BlogDto;
import com.example.projectbase.domain.entity.Blog;
import com.example.projectbase.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@UiV1
@AllArgsConstructor
public class BlogController extends BaseController {
    @Autowired
    private BlogService blogService;

    @GetMapping(UrlConstant.Blog.CREATE_BLOG)
    public String getPage() {
        return "upload_post";
    }

    @PostMapping(UrlConstant.Blog.CREATE_BLOG)
    public String createPost(Model model, @ModelAttribute BlogDto blogDto) {
        if (getCurrentUser() == null) {
            model.addAttribute("loginFirst", "Please login first!");
            return "upload_post";
        }
        blogDto.setUserId(getCurrentUser().getId());
        Blog blog = blogService.createBlog(blogDto);
        if (blog != null) {
            model.addAttribute("success", "Upload blog successfully!");
        } else {
            model.addAttribute("error", "Upload blog failed!");
        }
        return "upload_post";
    }
}
