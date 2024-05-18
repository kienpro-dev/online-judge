package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.ExerciseDto;
import com.example.projectbase.domain.entity.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    @Mapping(target = "exUrl", ignore = true)
    Exercise toExercise(ExerciseDto exerciseDto);
}
