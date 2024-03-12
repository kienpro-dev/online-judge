package com.example.projectbase.security.jwt;

import com.example.projectbase.base.RestData;
import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.util.BeanUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class JwtAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

  @SneakyThrows
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
    MessageSource messageSource = BeanUtil.getBean(MessageSource.class);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    String message = messageSource.getMessage(ErrorMessage.UNAUTHORIZED, null, LocaleContextHolder.getLocale());
    response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(RestData.error(message)));
  }

}
