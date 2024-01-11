package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket>findByCode(String code);
    @Query(value = "SELECT MAX(CAST(SUBSTRING(code, 8) AS SIGNED)) FROM Ticket WHERE SUBSTRING(code, 1, 4) = :year", nativeQuery = true)
    Long findLatestSequenceNumber(@Param("year") String year);
    @Query(value = "SELECT SUM(tkt.total_price) AS total_revenue " +
            "FROM ticket tkt " +
            "JOIN shows sh on tkt.show_id = sh.id " +
            "JOIN movie mve on sh.movie_id = mve.id " +
            "WHERE mve.code = :movieCode " +
            "AND (sh.show_date BETWEEN :startDate AND :endDate)", nativeQuery = true)
    Long getMovieBookingRevenueByMovieCode(@Param("movieCode")String movieCode, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
