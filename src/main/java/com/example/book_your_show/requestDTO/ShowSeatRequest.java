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
    int priceOfPhysicallyHandicappedSeats;
    boolean isFoodAttachedForPHS;
    int priceOfSilverSeats;
    boolean isFoodAttachedForSS;
    int priceOfGoldSeats;
    boolean isFoodAttachedForGS;
    int priceOfPlatinumSeats;
    boolean isFoodAttachedForPS;
    int priceOfLoungers;
    boolean isFoodAttachedForLS;
    int priceOfSemiRecliners;
    boolean isFoodAttachedForSRS;
    int priceOfRecliners;
    boolean isFoodAttachedForRS;
}
