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
import com.example.projectbase.service.UserService;
import com.example.projectbase.util.PaginationUtil;
import com.example.projectbase.util.RandomPassUtil;
import com.example.projectbase.util.SendMailUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final SendMailUtil sendMailUtil;

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{String.valueOf(userId)}));
        return userMapper.toUserDto(user);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{String.valueOf(userId)}));
    }

    @Override
    public PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request) {
        //Pagination
        Pageable pageable = PaginationUtil.buildPageable(request, SortByDataConstant.USER);
        //Create Output
        return new PaginationResponseDto<>(null, null);
    }

    @Override
    public boolean createUser(UserCreateDto userCreateDto) {
        if (userRepository.existsByUsername(userCreateDto.getUsername()) || userRepository.existsByEmail(userCreateDto.getEmail())) {
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
        if (u == null) {
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

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<User> content = new ArrayList<>(users.getContent());
        content.sort((o1, o2) -> o2.calculateTotalPoints().compareTo(o1.calculateTotalPoints()));
        return new PageImpl<>(content, pageable, users.getTotalElements());
    }

    @Override
    public List<User> findTopUser() {
        List<User> content = userRepository.findAll();
        content.sort((o1, o2) -> o2.calculateTotalPoints().compareTo(o1.calculateTotalPoints()));
        return content.subList(0, Math.min(content.size(), 3));
    }

}
