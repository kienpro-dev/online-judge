package com.example.projectbase.validator;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.validator.annotation.ValidFileImage;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FileImageValidator implements ConstraintValidator<ValidFileImage, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
    if (file != null) {
      String contentType = file.getContentType();
      return isSupportedContentType(Objects.requireNonNull(contentType));
    }
    return false;
  }

  private boolean isSupportedContentType(String contentType) {
    return CommonConstant.CONTENT_TYPE_IMAGE.contains(contentType.substring("image/".length()));
  }

}
