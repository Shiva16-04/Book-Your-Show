package com.example.book_your_show.repository;

import com.example.book_your_show.entities.ScreenSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreSeatRepository extends JpaRepository<ScreenSeat, Integer> {
}
