package com.mbbm.app.service;

import com.mbbm.app.enums.EContactType;
import com.mbbm.app.http.request.SignupRequestDTO;
import com.mbbm.app.model.base.Address;
import com.mbbm.app.model.base.Contact;
import com.mbbm.app.model.base.User;
import com.mbbm.app.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public Contact getContactById(String userId) {
        Optional<Contact> contact = contactRepository.findById(Long.parseLong(userId));
        return contact.orElse(null);
    }

    public Contact buildNewContactObject(SignupRequestDTO signupRequestDTO, User user) {
       Contact contact = new Contact();
       contact.setType(EContactType.valueOf(signupRequestDTO.getContactType()));
       contact.setCode(signupRequestDTO.getCountryCode());
       contact.setValue(signupRequestDTO.getContactValue());
       contact.setUser(user);
       contactRepository.save(contact);
       return contact;
    }

}
