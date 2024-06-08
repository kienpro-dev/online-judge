package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends DateAuditing implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn(name = "role_id", referencedColumnName = "id")
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

    public Double calculateTotalPoints() {
        double totalPoints = 0;
        if (submissions != null) {
            for (Submission submission : submissions) {
                if ("success".equals(submission.getStatus())) {
                    totalPoints += submission.getExercise().getScore();
                }
            }
        }
        return totalPoints;
    }

    public Integer calculateTotalExercise() {
        Set<Long> uniqueExerciseIds = new HashSet<>();
        if (submissions != null) {
            for (Submission submission : submissions) {
                if ("success".equals(submission.getStatus())) {
                    uniqueExerciseIds.add(submission.getExercise().getId());
                }
            }
        }
        return uniqueExerciseIds.size();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
