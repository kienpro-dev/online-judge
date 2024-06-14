package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.UserUpdateDto;
import com.example.projectbase.domain.entity.Submission;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.service.SubmissionService;
import com.example.projectbase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@UiV1
@AllArgsConstructor
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private SubmissionService submissionService;

    @GetMapping(UrlConstant.User.GET_USER_PARAM)
    public String getPage(Model model, @RequestParam Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "perinformation";
    }

    @GetMapping(UrlConstant.User.STATISTIC_USER_PARAM)
    public String getPageStatistic(Model model,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size,
                                   @RequestParam Long id) {
        User user = userService.findUserById(id);
        Page<Submission> submissions = submissionService.getAllSubmissionByUserId(id, PageRequest.of(page, size));;
        model.addAttribute("submissions", submissions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", submissions.getTotalPages());
        model.addAttribute("user", user);
        return "statistics";
    }

    @GetMapping(UrlConstant.User.EDIT_USER_PARAM)
    public String getPageEdit(Model model, @RequestParam(name = "id") Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "edit_profile";
    }

    @PostMapping(UrlConstant.User.EDIT_USER_PARAM)
    public String updateUser(Model model, @ModelAttribute UserUpdateDto userUpdateDto) {
        User user = userService.updateUser(getCurrentUser().getId(), userUpdateDto);
        if (user != null) {
            model.addAttribute("success", "Update user successfully!");
        } else {
            model.addAttribute("error", "Update user failed!");
        }
        model.addAttribute("user", user);
        return "edit_profile";
    }
}
