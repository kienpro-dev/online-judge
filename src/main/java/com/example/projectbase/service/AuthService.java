package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.LoginRequestDto;
import com.example.projectbase.domain.dto.request.TokenRefreshRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.LoginResponseDto;
import com.example.projectbase.domain.dto.response.TokenRefreshResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

  LoginResponseDto login(LoginRequestDto request);

  TokenRefreshResponseDto refresh(TokenRefreshRequestDto request);

  CommonResponseDto logout(HttpServletRequest request);

}
