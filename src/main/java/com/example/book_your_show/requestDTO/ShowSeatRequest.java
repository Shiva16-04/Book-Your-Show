package com.example.book_your_show.requestDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowSeatRequest {
    int priceOfSilverSeats;
    int priceOfGoldSeats;
    int priceOfPlatinumSeats;
    int priceOfLoungers;
    int priceOfSemiRecliners;
    int priceOfRecliners;
}
