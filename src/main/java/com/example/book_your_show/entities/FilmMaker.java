package com.example.book_your_show.entities;

import com.example.book_your_show.enums.ProfessionEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Column(nullable = false,unique = true)
    String code;
    @Column(nullable = false)
    String name;
    LocalDate dateOfBirth;
    String birthPlace;
    @Column(nullable = false, unique = true)
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "should be a valid email address")
    String emailId;
    @ManyToMany(cascade = CascadeType.PERSIST)
    List<Profession>professionList;
    @ManyToMany(mappedBy = "filmMakersList")
    List<Movie>movieList;
}
