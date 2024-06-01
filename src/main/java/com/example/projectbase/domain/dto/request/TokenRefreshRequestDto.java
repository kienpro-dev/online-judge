package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenRefreshRequestDto {

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String refreshToken;

}
