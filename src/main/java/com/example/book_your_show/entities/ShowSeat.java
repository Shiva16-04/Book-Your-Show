package com.example.book_your_show.entities;

import com.example.book_your_show.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"show", "screenSeat", "ticket"})
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false) //seat number and seat type will get it from the parent
    String showSeatNo;
    @Column(nullable = false)
    SeatType showSeatType;
    @Column(nullable = false)
    int cost;
    @Column(nullable = false)
    boolean isAvailable;
    @Column(nullable = false)
    boolean isFoodAttached;
    @ManyToOne //show seat is child with respect to show //it is many to one because many show seats belong to one show...and one show seat cannot belongto multiple shows as it is unique to each show
    Show show;
    @ManyToOne //child with respect to screen seat
    ScreenSeat screenSeat;
    @ManyToOne(cascade = CascadeType.ALL)
    Ticket ticket;


}
