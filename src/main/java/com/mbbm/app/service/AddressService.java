package com.mbbm.app.service;

import com.mbbm.app.http.request.SignupRequestDTO;
import com.mbbm.app.model.base.Address;
import com.mbbm.app.model.base.Profile;
import com.mbbm.app.model.base.User;
import com.mbbm.app.repository.AddressRepository;
import com.mbbm.app.repository.ProfileRepository;
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

    public Address buildNewAddressObject(SignupRequestDTO signupRequestDTO, User user) {
        Address address = new Address();
        address.setCountry(signupRequestDTO.getCountry());
        address.setState(signupRequestDTO.getState());
        address.setCity(signupRequestDTO.getCity());
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }

}
