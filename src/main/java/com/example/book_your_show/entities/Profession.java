package com.example.book_your_show.entities;

import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.enums.ProfessionEnum;
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
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    ProfessionEnum profession;
    @ManyToMany(mappedBy = "professionList", cascade = CascadeType.ALL)
    List<FilmMaker> filmMakerList;
}
