package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.domain.entity.Blog;
import com.example.projectbase.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@UiV1
public class HomeController extends BaseController{
    @Autowired
    private BlogService blogService;
    @GetMapping
    public String getPage(Model model) {
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("listBlogs", blogs);
        model.addAttribute("currentUser", getCurrentUser());
        return "home";
    }
}
