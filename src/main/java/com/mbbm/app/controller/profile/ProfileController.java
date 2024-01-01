package com.mbbm.app.controller.profile;

import com.mbbm.app.http.request.ProfileAvatarUpdateRequestDTO;
import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.http.response.constants.ResponseMessages;
import com.mbbm.app.model.base.Profile;
import com.mbbm.app.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mohamed.bendary
 * 1- get user profile information
 * 2- update profile picture (avatar)
 * 3- update user-profile information
 * 4- activate profile after registration
 * 5- block profile
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private Logger logger = LoggerFactory.getLogger(ProfileController.class);
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}/info")
    public ResponseEntity<?> getProfileInfo(@PathVariable String profileId) {
        Profile profile = profileService.getProfileById(profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    //TODO :: REVISIT, REFACTOR RESPONSE MESSAGE CONSTRUCTING
    @PostMapping("/{profileId}/updateAvatar")
    public ResponseEntity<?> updateProfileAvatar(@PathVariable String profileId, @RequestBody ProfileAvatarUpdateRequestDTO profileAvatarUpdateRequestDTO) {
        logger.info("Starting updating avatar for profile = " + profileId);
        ResponseMessage responseMessage = new ResponseMessage();
        HttpStatus httpStatus;
        boolean isAvatarUpdatedSuccessfully = profileService.updateProfileAvatar(profileId, profileAvatarUpdateRequestDTO);
        if(isAvatarUpdatedSuccessfully){
            logger.info("avatar updated successfully for profile = " + profileId);
            httpStatus = HttpStatus.OK;
            responseMessage.setMessage(ResponseMessages.PROFILE_AVATAR_UPDATED_SUCCESSFULLY);
        }else{
            logger.error("updating avatar failed for profile = " + profileId);
            httpStatus = HttpStatus.BAD_REQUEST;
            responseMessage.setMessage(ResponseMessages.PROFILE_AVATAR_UPDATING_FAILED);
        }
        return new ResponseEntity<>(responseMessage, httpStatus);
    }

    @GetMapping("/{profileId}/activate")
    public ResponseEntity<?> activate(@PathVariable String profileId) {
        ResponseMessage responseMessage = new ResponseMessage();
        HttpStatus httpStatus;
        boolean isProfileActivated = profileService.activateProfile(profileId);
        if(isProfileActivated){
            httpStatus = HttpStatus.OK;
            responseMessage.setMessage(ResponseMessages.PROFILE_ACTIVATED_SUCCESSFULLY);
        }else{
            httpStatus = HttpStatus.BAD_REQUEST;
            responseMessage.setMessage(ResponseMessages.PROFILE_ACTIVATING_FAILED);
        }
        return new ResponseEntity<>(responseMessage, httpStatus);
    }

    @GetMapping("/{profileId}/block")
    public ResponseEntity<?> block(@PathVariable String profileId) {
        ResponseMessage responseMessage = new ResponseMessage();
        HttpStatus httpStatus;
        boolean isProfileBlocked = profileService.blockProfile(profileId);
        if(isProfileBlocked){
            httpStatus = HttpStatus.OK;
            responseMessage.setMessage(ResponseMessages.PROFILE_BLOCKED_SUCCESSFULLY);
        }else{
            httpStatus = HttpStatus.BAD_REQUEST;
            responseMessage.setMessage(ResponseMessages.PROFILE_BLOCKING_FAILED);
        }
        return new ResponseEntity<>(responseMessage, httpStatus);
    }

}
