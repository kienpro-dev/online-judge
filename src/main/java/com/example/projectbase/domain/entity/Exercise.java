package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exercise")
    @JsonIgnore
    private List<Submission> submissions;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "contests_exercises")
    @JsonIgnore
    private List<Contest> exercises_contests;

    public long countSuccessSubmissions() {
        return submissions.stream().filter(submission -> submission.getStatus().equals("true")).count();
    }

    public long countFailedSubmissions() {
        return submissions.stream().filter(submission -> submission.getStatus().equals("false")).count();
    }

    public double calculateCorrectPercentage() {
        long totalSubmissions = submissions.size();
        long correctSubmissions = countSuccessSubmissions();

        if (totalSubmissions == 0) {
            return 0.0;
        }

        return ((double) correctSubmissions / totalSubmissions) * 100;
    }

    public boolean isSolvedByCurrentUser(String currentUsername) {
        return submissions.stream()
                          .map(Submission::getUser)
                          .filter(Objects::nonNull)
                          .anyMatch(user -> user.getUsername().equals(currentUsername));
    }

}
