package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User extends DateAuditing {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
  private String id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Nationalized
  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String timezone;

  @Column(nullable = false)
  private String defaultLanguage;

  //Link to table Role
  @ManyToOne
  @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_USER_ROLE"))
  private Role role;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private List<Exercise> exercises;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private List<Blog> blogs;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private List<Submission> submissions;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private List<Contest> contests;

  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "contests_users")
  @JsonIgnore
  private List<Contest> users_contests;

}
