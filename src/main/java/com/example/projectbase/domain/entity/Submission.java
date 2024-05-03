package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "submissions")
public class Submission extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @Lob
    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_SUBMISSION_USER"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "FK_SUBMISSION_EXERCISE"))
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "contest_id", foreignKey = @ForeignKey(name = "FK_SUBMISSION_"))
    private Contest contest;
}
