package com.example.projectbase.service;

import com.example.projectbase.domain.entity.Contest;

import java.util.List;

public interface ContestService {
    List<Contest> getAllContest();

    Contest getContestDetail(Long id);
}
