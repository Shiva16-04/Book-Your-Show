package com.example.book_your_show.entities;

import com.example.book_your_show.enums.GenreEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Entity
@Table
@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    GenreEnum genre;
    @ManyToMany(mappedBy = "genreList", cascade = CascadeType.ALL)
    List<Movie> movieList;
}
