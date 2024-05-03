package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "exercises")
public class Exercise extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private String exUrl;

    @Column(nullable = false)
    @Lob
    private String testInput;

    @Column(nullable = false)
    @Lob
    private String testOutput;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_EXERCISE_USER"))
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exercise")
    @JsonIgnore
    private List<Submission> submissions;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "contests_exercises")
    @JsonIgnore
    private List<Contest> exercises_contests;
}
