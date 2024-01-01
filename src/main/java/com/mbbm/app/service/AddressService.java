package com.mbbm.app.service;

import com.mbbm.app.http.request.NewUserRequestDTO;
import com.mbbm.app.model.base.Address;
import com.mbbm.app.model.base.User;
import com.mbbm.app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address getAddressById(String userId) {
        Optional<Address> address = addressRepository.findById(Long.parseLong(userId));
        return address.orElse(null);
    }

    public Address buildNewAddressObject(NewUserRequestDTO newUserRequestDTO, User user) {
        Address address = new Address();
        address.setCountry(newUserRequestDTO.getCountry());
        address.setState(newUserRequestDTO.getState());
        address.setCity(newUserRequestDTO.getCity());
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }

}
