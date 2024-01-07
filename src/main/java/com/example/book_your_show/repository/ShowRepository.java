package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Integer> {

    Optional<Show> findByCode(String showCode);
    @Query(value = "SELECT MAX(CAST(SUBSTRING(code, 8) AS SIGNED)) FROM shows WHERE SUBSTRING(code, 1, 4) = :year", nativeQuery = true)
    Long findLatestSequenceNumber(@Param("year") String year);

    @Query(value = "SELECT sh.* " +
            "FROM shows sh " +
            "JOIN shows_screen_list sl ON sh.id = sl.show_list_id " +
            "JOIN screen sc ON sl.screen_list_id = sc.id " +
            "JOIN theatre t ON sc.theatre_id = t.id " +
            "WHERE sc.screen_number = :screenNumber " +
            "AND ((sh.start_time BETWEEN :startTimeRangeStart AND :startTimeRangeEnd) OR (sh.end_time BETWEEN :endTimeRangeStart AND :endTimeRangeEnd)) " +
            "AND t.code = :theatreCode", nativeQuery = true)
    List<Show> findShowsByScreenNumberAndTimeRangeAndTheatreCode(
            @Param("screenNumber") String screenNumber,
            @Param("startTimeRangeStart") LocalDateTime startTimeRangeStart,
            @Param("startTimeRangeEnd") LocalDateTime startTimeRangeEnd,
            @Param("endTimeRangeStart") LocalDateTime endTimeRangeStart,
            @Param("endTimeRangeEnd") LocalDateTime endTimeRangeEnd,
            @Param("theatreCode") String theatreCode);


}
