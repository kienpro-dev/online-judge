package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

  UserDto getUserById(Long userId);

  PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request);

  boolean createUser(UserCreateDto userCreateDto);

  boolean resetPassword(String email);

  Page<User> getAllUsers(Pageable pageable);

  List<User> findTopUser();
}
