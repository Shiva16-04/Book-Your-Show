package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Address;
import com.example.book_your_show.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre,Integer> {
//    Optional<Theatre>findByTheatreNameAndAddress(String movieName, Address address);
    Optional<Theatre>findByCode(String code);
    @Query(value = "SELECT MAX(CAST(SUBSTRING(code, 8) AS SIGNED)) FROM Theatre WHERE SUBSTRING(code, 1, 4) = :year", nativeQuery = true)
    Long findLatestSequenceNumber(@Param("year") String year);
}
