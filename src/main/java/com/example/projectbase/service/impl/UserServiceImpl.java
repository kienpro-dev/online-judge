package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.RoleConstant;
import com.example.projectbase.constant.SortByDataConstant;
import com.example.projectbase.domain.dto.common.DataMailDto;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.UserMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.RoleRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.UserService;
import com.example.projectbase.util.PaginationUtil;
import com.example.projectbase.util.RandomPassUtil;
import com.example.projectbase.util.SendMailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  private final SendMailUtil sendMailUtil;

  @Override
  public UserDto getUserById(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
    return userMapper.toUserDto(user);
  }

  @Override
  public PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request) {
    //Pagination
    Pageable pageable = PaginationUtil.buildPageable(request, SortByDataConstant.USER);
    //Create Output
    return new PaginationResponseDto<>(null, null);
  }

  @Override
  public UserDto getCurrentUser(UserPrincipal principal) {
    User user = userRepository.getUser(principal);
    return userMapper.toUserDto(user);
  }

  @Override
  public boolean createUser(UserCreateDto userCreateDto) {
    if(userRepository.existsByUsername(userCreateDto.getUsername()) || userRepository.existsByEmail(userCreateDto.getEmail())) {
      return false;
    }
    User user = userMapper.toUser(userCreateDto);
    user.setRole(roleRepository.findByRoleName(RoleConstant.USER));
    user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
    userRepository.save(user);
    return true;
  }

  @Override
  public boolean resetPassword(String email) {
    User u = userRepository.findUserByEmail(email);
    if(u == null) {
      return false;
    }
    String newPassword = RandomPassUtil.random();

    u.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(u);
    DataMailDto dataMailDto = new DataMailDto();
    dataMailDto.setTo(email);
    dataMailDto.setSubject("Mật khẩu mới của bạn là: " + newPassword);

    Map<String, Object> properties = new HashMap<>();
    properties.put("username", u.getUsername());
    properties.put("newPassword", newPassword);

    dataMailDto.setProperties(properties);

    try {
      sendMailUtil.sendEmailWithHTML(dataMailDto, "sendmail.html");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

}
