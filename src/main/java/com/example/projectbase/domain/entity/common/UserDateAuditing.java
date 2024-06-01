package com.example.projectbase.domain.entity.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class UserDateAuditing extends DateAuditing {

  @CreatedBy
  @Column(updatable = false)
  private String createdBy;

  @LastModifiedBy
  @Column(nullable = false)
  private String lastModifiedBy;

}
