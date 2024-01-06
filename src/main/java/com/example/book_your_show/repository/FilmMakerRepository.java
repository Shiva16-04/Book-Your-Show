package com.example.book_your_show.repository;

import com.example.book_your_show.entities.FilmMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FilmMakerRepository extends JpaRepository<FilmMaker,Integer> {
    Optional<FilmMaker>findByEmailId(String emailId);
    List<FilmMaker>findByCodeIn(List<String>filmMakerCodeList);

    @Query(value = "SELECT MAX(CAST(SUBSTRING(code, 8) AS SIGNED)) FROM film_maker WHERE SUBSTRING(code, 1, 4) = :year", nativeQuery = true)
    Long findLatestSequenceNumber(@Param("year") String year);
}
