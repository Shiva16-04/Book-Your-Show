package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Movie;
import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.Show;
import com.example.book_your_show.requestDTO.ShowRequest;

public class ShowTransformer {
    public static Show ShowRequestToShow(ShowRequest showRequest){
        return Show.builder()
                .date(showRequest.getDate())
                .time(showRequest.getTime())
                .build();
    }
}
