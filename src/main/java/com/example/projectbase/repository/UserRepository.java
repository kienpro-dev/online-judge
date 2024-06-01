package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  User findUserByEmail(String email);

  User findByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.id = ?1")
  Optional<User> findUserById(Long id);

  @Query("SELECT u FROM User u WHERE u.username = ?1")
  User findByUsername(String username);

}
