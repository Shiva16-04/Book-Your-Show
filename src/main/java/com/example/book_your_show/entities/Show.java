package com.example.book_your_show.entities;

import com.example.book_your_show.enums.Format;
import com.example.book_your_show.enums.LanguageEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
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
    LocalDate date;
    LocalTime time;

    @ManyToOne //here show is the child with respect to the movie
    Movie movie;
    @ManyToMany //here show is the child with respect to the Screen
    List<Screen> screenList;
    @OneToMany (mappedBy = "show", cascade = CascadeType.ALL) //parent with respect to the show seat
    List<ShowSeat>showSeatList;
    @OneToMany (mappedBy = "show", cascade = CascadeType.ALL)
    List<Ticket>ticketList;
}
