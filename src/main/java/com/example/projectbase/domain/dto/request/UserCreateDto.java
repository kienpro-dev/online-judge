package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {

  @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private String username;

  @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$", message = ErrorMessage.INVALID_FORMAT_PASSWORD)
  private String password;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String fullName;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = ErrorMessage.INVALID_FORMAT_EMAIL)
  private String email;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String timezone;

  @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
  private String defaultLanguage;
}
