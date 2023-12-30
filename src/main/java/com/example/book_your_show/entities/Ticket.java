package com.example.book_your_show.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ticket")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    int totalPrice;
    @OneToMany(mappedBy = "ticket")
//    @JoinColumn(nullable = false)--doubt
    List<ShowSeat>bookedSeats;
    @ManyToOne
    Show show;
    @ManyToOne
    User user;
}
