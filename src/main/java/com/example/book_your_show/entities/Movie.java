package com.example.book_your_show.entities;

import com.example.book_your_show.enums.FilmCertificationCategory;
import com.example.book_your_show.enums.FormatEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"formatList", "languagesReleasedIn", "genreList", "showList", "filmMakersList"})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String code;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    LocalDate releaseDate;
    @Column(nullable = false)
    LocalTime screenTime;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    List<Format> formatList;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    FilmCertificationCategory filmCertificationCategory;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    List<Language> languagesReleasedIn;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    List<Genre> genreList;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    List<Show>showList;
    @ManyToMany
    List<FilmMaker>filmMakersList;

}
