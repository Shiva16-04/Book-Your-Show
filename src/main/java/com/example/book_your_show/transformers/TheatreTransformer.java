package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Address;
import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.requestDTO.TheatreRequest;
import com.example.book_your_show.responseDTO.ShowResponseTheatre;
import com.example.book_your_show.responseDTO.TheatreResponseShow;
import com.example.book_your_show.service.impl.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TheatreTransformer {
    @Autowired
    private TheatreServiceImpl theatreServiceImpl;
    public static Theatre theatreRequestToTheatre(TheatreRequest theatreRequest){
        return Theatre.builder()
                .name(theatreRequest.getTheatreName())
                .numberOfScreens(0)
                .screens(new ArrayList<>())
                .build();
        //need to add screen's list and vice versa as part of the bidirectional mapping
    }
    public static TheatreResponseShow theatreAndAddressToTheatreResponseShow(String theatreName, Address address, List<ShowResponseTheatre>showResponseTheatreList){
        return TheatreResponseShow.builder()
                .theatreName(theatreName)
                .locality(address.getLocality())
                .city(address.getCity().toString())
                .theatreLocationUrl(address.getLocationUrl())
                .showResponseTheatreList(showResponseTheatreList)
                .build();
    }
}
