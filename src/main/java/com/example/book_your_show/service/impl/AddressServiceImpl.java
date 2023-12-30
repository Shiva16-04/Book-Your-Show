package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Address;
import com.example.book_your_show.exceptions.AddressAlreadyPresentException;
import com.example.book_your_show.exceptions.InvalidAddressCodeException;
import com.example.book_your_show.generators.AddressCodeGenerator;
import com.example.book_your_show.repository.AddressRepository;
import com.example.book_your_show.requestDTO.AddressRequest;
import com.example.book_your_show.service.AddressService;
import com.example.book_your_show.transformers.AddressTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressCodeGenerator addressCodeGenerator;

    public String addAddress(AddressRequest addressRequest)throws Exception{
        Optional<Address>optionalAddress=addressRepository.findByLocationUrl(addressRequest.getLocationUrl());
        if(optionalAddress.isPresent()){
            throw new AddressAlreadyPresentException("Address is already present in the database with particular location with address id: "+optionalAddress.get().getCode());
        }
        Address address= AddressTransformer.AddressRequestToAddress(addressRequest);
        String code= addressCodeGenerator.generate("ADR_"+address.getCity().getCityCode());
        address.setCode(code);
        Address savedAddress=addressRepository.save(address);
        return savedAddress.getCode();
    }
    public Address getAddressByAddressCode(String addressCode)throws Exception{
        Optional<Address>optionalAddress=addressRepository.findByCode(addressCode);
        if(optionalAddress.isEmpty()){
            throw new InvalidAddressCodeException("Provided address code is invalid. Check again once");
        }
        return optionalAddress.get();
    }
}
