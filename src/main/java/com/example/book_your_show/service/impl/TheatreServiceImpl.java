package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Address;
import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.generators.TheatreCodeGenerator;
import com.example.book_your_show.repository.TheatreRepository;
import com.example.book_your_show.requestDTO.AddressRequest;
import com.example.book_your_show.requestDTO.TheatreRequest;
import com.example.book_your_show.service.AddressService;
import com.example.book_your_show.service.ScreenService;
import com.example.book_your_show.service.TheatreService;
import com.example.book_your_show.transformers.TheatreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private AddressService addressService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private TheatreCodeGenerator theatreCodeGenerator;

    public String addTheatre(TheatreRequest theatreRequest)throws Exception{
        Theatre theatre= TheatreTransformer.theatreRequestToTheatre(theatreRequest);

        AddressRequest addressRequest=theatreRequest.getAddressRequest();
        String addressCode=addressService.addAddress(addressRequest);
        Address address=addressService.getAddressByAddressCode(addressCode);

        String theatreCode=theatreCodeGenerator.generate("THR");

        address.setTheatre(theatre); //foreign key is set from address side
        theatre.setAddress(address); //bidirectionally mapping

        theatre.setCode(theatreCode);

        List<Screen>screenList=screenService.createScreens(theatre,theatreRequest);
        theatre.setScreens(screenList);

        theatreRepository.save(theatre); //cascading the save effect to address and screens
        return theatre.getCode();
    }
}
