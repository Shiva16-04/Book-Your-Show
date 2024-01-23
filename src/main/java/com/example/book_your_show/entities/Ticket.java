package com.example.book_your_show.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String code;
    @Column(nullable = false)
    int totalPrice;
    @OneToMany(mappedBy = "ticket")
    List<ShowSeat>bookedSeats;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Show show;
    @ManyToOne
    User user;
}
