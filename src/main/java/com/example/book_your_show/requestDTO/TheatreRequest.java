package com.example.book_your_show.requestDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TheatreRequest {
    String theatreName;
    int noOfScreens;
    AddressRequest addressRequest;
    List<ScreenRequest> screenRequestList;
}
