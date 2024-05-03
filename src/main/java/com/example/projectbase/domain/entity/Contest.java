package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "contests")
public class Contest extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime expiredDate;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_CONTEST_USER"))
    private User user;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contests_users",
            joinColumns = @JoinColumn(name = "contest_id", foreignKey = @ForeignKey(name = "FK_CONTEST_USER1")),
            inverseJoinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_CONTEST_USER2")))
    private List<User> contests_users = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contests_exercises",
            joinColumns = @JoinColumn(name = "contest_id", foreignKey = @ForeignKey(name = "FK_CONTEST_EXERCISE1")),
            inverseJoinColumns = @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "FK_CONTEST_EXERCISE2")))
    private List<Exercise> contests_exercises = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Submission> submissions;
}
