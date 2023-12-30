package com.example.book_your_show.service;

import com.example.book_your_show.entities.Address;
import com.example.book_your_show.requestDTO.AddressRequest;

public interface AddressService {
    public String addAddress(AddressRequest addressRequest)throws Exception;
    public Address getAddressByAddressCode(String addressCode)throws Exception;
}
