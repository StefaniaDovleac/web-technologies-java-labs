package com.unibuc.lab10.service;

import com.unibuc.lab10.domain.Address;
import com.unibuc.lab10.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address create(Address address){
        return repository.save(address);
    }

    public Address getBy(Long id){
        return repository.findBy(id)
                .orElseThrow(() -> new RuntimeException("Not found!"));
    }
}
