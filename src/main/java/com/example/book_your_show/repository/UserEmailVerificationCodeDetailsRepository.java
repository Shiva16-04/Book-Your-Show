package com.example.book_your_show.repository;

import com.example.book_your_show.entities.UserEmailVerificationCodeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEmailVerificationCodeDetailsRepository extends JpaRepository<UserEmailVerificationCodeDetails, Integer> {
    Optional<UserEmailVerificationCodeDetails> findByEmailId(String email);
}
