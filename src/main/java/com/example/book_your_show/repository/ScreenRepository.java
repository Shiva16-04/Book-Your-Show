package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {
    Optional<Screen> findByTheatreCodeAndScreenNumber(String theatreCode, String screenNumber);

}
