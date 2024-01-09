package com.example.book_your_show.repository;

import com.example.book_your_show.entities.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    List<ShowSeat>findByShowCodeAndShowSeatNoIn(String showCode, List<String>showSeatList);
}
