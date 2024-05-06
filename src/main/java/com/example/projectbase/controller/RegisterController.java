package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@UiV1
public class RegisterController {
    private final UserService userService;

    @GetMapping(UrlConstant.Auth.REGISTER)
    public String getPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(UrlConstant.Auth.REGISTER)
    public String register(@ModelAttribute UserCreateDto userCreateDto, @RequestParam String pass2, Model model) {
        if(userService.createUser(userCreateDto)) {
            model.addAttribute("user", new User());
            model.addAttribute("success", "Đăng ký thành công!");
        } else {
            model.addAttribute("error", "Đăng ký thất bại!");
        }
        return "register";
    }
}
