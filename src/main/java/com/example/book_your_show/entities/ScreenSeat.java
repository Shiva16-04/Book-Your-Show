package com.example.book_your_show.entities;

import com.example.book_your_show.enums.SeatType;
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
public class ScreenSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String screenSeatNumber;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    SeatType screenSeatType;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Screen screen;
    @OneToMany(mappedBy = "screenSeat", cascade = CascadeType.ALL)
    List<ShowSeat> showSeatList;

}
