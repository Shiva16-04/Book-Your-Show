package com.example.book_your_show.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowResponseTheatre {
    String movieName;
    String screenNumber;
    LocalDate showDate;
    LocalTime showTime;
    String language;
    String format;
    List<ShowSeatResponse> showSeatResponseList;
}
