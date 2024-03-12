package com.example.projectbase.validator.annotation;

import com.example.projectbase.validator.FileImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Constraint(validatedBy = {FileImageValidator.class})
public @interface ValidFileImage {

  String message() default "invalid.file-image";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
