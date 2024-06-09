package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.domain.entity.Blog;
import com.example.projectbase.domain.entity.Exercise;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.service.BlogService;
import com.example.projectbase.service.ExerciseService;
import com.example.projectbase.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public String getPage(Model model) {
        List<Blog> blogs = blogService.getAllBlogs();
        List<User> topUsers = userService.findTopUser();
        List<Exercise> recentExercise = exerciseService.findRecentExercise();
        model.addAttribute("listBlogs", blogs);
        model.addAttribute("listUsers", topUsers);
        model.addAttribute("listExercise", recentExercise);
        return "home";
    }
}
