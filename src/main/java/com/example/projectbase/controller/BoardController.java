package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@UiV1
@AllArgsConstructor
public class BoardController extends BaseController{
    private final UserService userService;
    @GetMapping(UrlConstant.Board.GET_USER_BOARD)
    public String getPage(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<User> users = userService.getAllUsers(PageRequest.of(page, size));

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", users.getTotalPages());

        return "user_leaderboard";
    }
}
