package com.example.projectbase.service.impl;

import com.example.projectbase.domain.entity.Contest;
import com.example.projectbase.repository.ContestRepository;
import com.example.projectbase.service.ContestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContestServiceImpl implements ContestService {
    private final ContestRepository contestRepository;
    @Override
    public List<Contest> getAllContest() {
        return contestRepository.findAll();
    }

    @Override
    public Contest getContestDetail(Long id) {
        return contestRepository.findById(id).orElse(null);
    }
}
