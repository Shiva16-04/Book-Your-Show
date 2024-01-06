package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Format;
import com.example.book_your_show.entities.Language;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormatRepository extends JpaRepository<Format, Integer> {
    Optional<Format> findByName(FormatEnum formatEnum);
    List<Format> findByNameIn(List<FormatEnum> formatEnumList);
}
