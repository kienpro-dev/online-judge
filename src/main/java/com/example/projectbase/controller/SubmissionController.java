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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@UiV1
@AllArgsConstructor
public class SubmissionController extends BaseController {
    @Autowired
    private SubmissionService submissionService;

    @GetMapping(UrlConstant.Submission.GET_SUBMISSIONS)
    public String getPage(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size,
                          @RequestParam(name = "id", required = false) Long id,
                          @RequestParam(name = "userId", required = false) Long userId) {
        Page<Submission> submissions = null;
        if (id == null) {
            if (userId == null) {
                submissions = submissionService.getAllSubmissions(PageRequest.of(page, size));
            } else {
                submissions = submissionService.getAllSubmissionByUserId(userId, PageRequest.of(page, size));
            }
        } else {
            submissions = submissionService.getAllSubmissionsByExerciseId(id, PageRequest.of(page, size));
        }
        model.addAttribute("submissions", submissions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", submissions.getTotalPages());
        if (id != null) {
            model.addAttribute("exeId", id);
        }
        if (userId != null) {
            model.addAttribute("userId", userId);
        }
        return "submissions";
    }

    @GetMapping(UrlConstant.Submission.GET_SUBMISSION)
    public String getSubmissionDetail(Model model,
                                      @PathVariable Long submissionId) {
        Submission submission = submissionService.getSubmissionById(submissionId);
        model.addAttribute("submission", submission);
        return "submission_detail";
    }
}
