package com.example.projectbase.controller.rest;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.BlogDto;
import com.example.projectbase.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RestApiV1
public class RestBlogController {
    @Autowired
    private final BlogService blogService;

    @Operation(summary = "API get blogs")
    @GetMapping(UrlConstant.Blog.GET_BLOGS)
    public ResponseEntity<?> getBlogs(@RequestParam(name = "page", defaultValue = "1") int page,
                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        return VsResponseUtil.success(blogService.getAllBlogs());
    }

    @Operation(summary = "API create blogs")
    @PostMapping(UrlConstant.Blog.CREATE_BLOG)
    public ResponseEntity<?> createBlogs(@ModelAttribute BlogDto blogDto) {
        return VsResponseUtil.success(blogService.createBlog(blogDto));
    }
}
