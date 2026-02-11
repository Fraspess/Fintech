package org.example.repositories;

import jakarta.validation.constraints.NotBlank;
import org.example.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByResetPasswordToken(@NotBlank String token);
}
