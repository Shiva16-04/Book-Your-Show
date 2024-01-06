package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Genre;
import com.example.book_your_show.entities.Language;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(GenreEnum genreEnum);
    List<Genre>findByNameIn(List<GenreEnum>genreEnumList);
}
