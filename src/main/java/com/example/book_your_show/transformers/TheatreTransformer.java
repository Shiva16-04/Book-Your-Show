package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.requestDTO.TheatreRequest;
import com.example.book_your_show.service.impl.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class TheatreTransformer {
    @Autowired
    private TheatreServiceImpl theatreServiceImpl;
    public static Theatre theatreRequestToTheatre(TheatreRequest theatreRequest){
        return Theatre.builder()
                .name(theatreRequest.getTheatreName())
                .numberOfScreens(theatreRequest.getNoOfScreens())
                .screens(new ArrayList<>())
                .build();
        //need to add screen's list and vice versa as part of the bidirectional mapping
    }
}
