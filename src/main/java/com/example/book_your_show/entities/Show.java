package com.example.book_your_show.entities;

import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shows")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String code;
    @Column(nullable = false)
    LocalDate date;
    @Column(nullable = false)
    LocalDateTime startTime;
    @Column(nullable = false)
    LocalDateTime endTime;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    LanguageEnum languageEnum;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    FormatEnum formatEnum;
    @ManyToOne //here show is the child with respect to the movie
    Movie movie;
    @ManyToMany //here show is the child with respect to the Screen
    List<Screen> screenList;
    @OneToMany (mappedBy = "show", cascade = CascadeType.ALL) //parent with respect to the show seat
    List<ShowSeat>showSeatList;
    @OneToMany (mappedBy = "show", cascade = CascadeType.ALL)
    List<Ticket>ticketList;
}
