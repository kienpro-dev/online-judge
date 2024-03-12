package com.example.projectbase.domain.dto.response;

import com.example.projectbase.domain.dto.common.DateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto extends DateAuditingDto {

  private String id;

  private String username;

  private String firstName;

  private String lastName;

  private String roleName;

}

