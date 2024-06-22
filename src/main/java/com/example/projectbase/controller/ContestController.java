package com.example.projectbase.controller;

import com.example.projectbase.base.UiV1;
import com.example.projectbase.service.ContestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@UiV1
public class ContestController {
    @Autowired
    private ContestService contestService;
}
