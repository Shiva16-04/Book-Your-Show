package com.example.book_your_show.entities;

import com.example.book_your_show.enums.ProfessionEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table
@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmMaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String name;
    @ManyToMany
    List<Profession>professionList;
    LocalDate dateOfBirth;
    String birthPlace;
    @ManyToMany(mappedBy = "filmMakersList")
    List<Movie>movieList;
}
