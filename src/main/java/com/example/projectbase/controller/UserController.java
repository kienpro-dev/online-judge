package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@UiV1
@AllArgsConstructor
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @GetMapping(UrlConstant.User.GET_USER_PARAM)
    public String getPage(Model model, @RequestParam Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "perinformation";
    }
}
