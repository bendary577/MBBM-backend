package com.mbbm.app.service;

import com.mbbm.app.model.base.Profile;
import com.mbbm.app.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public Profile getProfileById(String userId) {
        Optional<Profile> profile = profileRepository.findById(Long.parseLong(userId));
        return profile.orElse(null);
    }

}
