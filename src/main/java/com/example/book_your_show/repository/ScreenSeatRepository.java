package com.example.book_your_show.repository;

import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScreenSeatRepository extends JpaRepository<ScreenSeat, Integer> {
    @Query(value = "SELECT DISTINCT ss.* "+
            "FROM theatre thr " +
            "JOIN screen sc ON thr.id = sc.theatre_id " +
            "JOIN screen_seat ss ON sc.id = ss.screen_id " +
            "WHERE (:theatreCode IS NULL OR thr.code = :theatreCode) " +
            "AND (:screenNumber IS NULL OR sc.screen_number = :screenNumber) " +
            "AND (:screenSeatNumber IS NULL OR ss.screen_seat_number = :screenSeatNumber) "
            , nativeQuery = true)
    List<ScreenSeat> findFilteredTheatreShowResponseList(
            @Param("theatreCode")String theatreCode,
            @Param("screenNumber")String screenNumber,
            @Param("screenSeatNumber")String screenSeatNumber
            );
}
