package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket>findByCode(String code);
    @Query(value = "SELECT MAX(CAST(SUBSTRING(code, 8) AS SIGNED)) FROM Ticket WHERE SUBSTRING(code, 1, 4) = :year", nativeQuery = true)
    Long findLatestSequenceNumber(@Param("year") String year);
}
