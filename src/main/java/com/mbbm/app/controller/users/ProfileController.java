package com.mbbm.app.controller.users;

import com.mbbm.app.model.base.Profile;
import com.mbbm.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 1- get user profile information
 * 2- update profile picture (avatar)
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}/info")
    public ResponseEntity<?> getProfileInfo(@PathVariable String profileId) {
        Profile profile = profileService.getProfileById(profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

}
