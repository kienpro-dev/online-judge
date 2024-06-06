package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.entity.Submission;
import com.example.projectbase.service.SubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@UiV1
@AllArgsConstructor
public class SubmissionController extends BaseController{
    @Autowired
    private SubmissionService submissionService;

    @GetMapping(UrlConstant.Submission.GET_SUBMISSIONS)
    public String getPage(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Submission> submissions = submissionService.getAllSubmissions(PageRequest.of(page, size));
        model.addAttribute("submissions", submissions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", submissions.getTotalPages());
        return "submissions";
    }
}
