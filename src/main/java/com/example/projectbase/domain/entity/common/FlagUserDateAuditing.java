package com.example.projectbase.domain.entity.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class FlagUserDateAuditing extends UserDateAuditing {

  @Column(nullable = false)
  private Boolean deleteFlag = Boolean.FALSE;

  @Column(nullable = false)
  private Boolean activeFlag = Boolean.TRUE;

}
