package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Profession;
import com.example.book_your_show.enums.ProfessionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {
    Optional<Profession> findByName(ProfessionEnum professionEnum);
    List<Profession> findByNameIn(List<ProfessionEnum> professionEnumList);
}
