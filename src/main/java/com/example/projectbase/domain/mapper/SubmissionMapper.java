package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.SubmissionDto;
import com.example.projectbase.domain.entity.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {
    @Mappings({
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "exercise", ignore = true),
            @Mapping(target = "contest", ignore = true),
    })
    Submission toSubmission(SubmissionDto submissionDto);
}
