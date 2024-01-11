package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Address;
import com.example.book_your_show.requestDTO.AddressRequest;


public class AddressTransformer {
    public static Address AddressRequestToAddress(AddressRequest addressRequest){
        return Address.builder()
                .plotNo(addressRequest.getPlotNo())
                .locality(addressRequest.getLocality())
                .city(addressRequest.getCity())
                .pinCode(addressRequest.getPinCode())
                .locationUrl(addressRequest.getLocationUrl())
                .build();
        //still theatre needs to be mapped
    }
}
