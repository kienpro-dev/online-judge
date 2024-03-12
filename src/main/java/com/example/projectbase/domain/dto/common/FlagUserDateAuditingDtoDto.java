package com.example.projectbase.domain.dto.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class FlagUserDateAuditingDtoDto extends UserDateAuditingDto {

  private Boolean deleteFlag;

  private Boolean activeFlag;

}
