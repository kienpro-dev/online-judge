package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.response.UserDto;

public interface UserService {

  UserDto getUserById(Long userId);

  PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request);

  boolean createUser(UserCreateDto userCreateDto);

  boolean resetPassword(String email);
}
