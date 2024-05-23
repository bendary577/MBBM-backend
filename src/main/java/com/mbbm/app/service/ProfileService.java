package com.mbbm.app.service;

import com.mbbm.app.controller.profile.ProfileController;
import com.mbbm.app.enums.EBlobType;
import com.mbbm.app.enums.ERole;
import com.mbbm.app.http.request.userProfile.ProfileAvatarUpdateRequestDTO;
import com.mbbm.app.http.request.authentication.UserRegistrationRequestDTO;
import com.mbbm.app.model.base.BlobEntity;
import com.mbbm.app.model.base.Profile;
import com.mbbm.app.model.base.User;
import com.mbbm.app.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BlobEntityService blobEntityService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(ProfileController.class);

    public Profile getProfileById(String profileId) {
        Optional<Profile> profile = profileRepository.findById(Long.parseLong(profileId));
        return profile.orElse(null);
    }

    public Profile buildNewProfileObject(UserRegistrationRequestDTO newUserRequestDTO, User user) {
        Profile profile = new Profile();
        profile.setType(ERole.valueOf(newUserRequestDTO.getUserType()));
        profile.setTimestamp(new Date().toString());
        //TODO : generate random code for each profile
        profile.setCode("v8571233fd49234asd");
        profile.setUser(user);
        profileRepository.save(profile);
        return profile;
    }

    public boolean updateProfileAvatar(String profileId, ProfileAvatarUpdateRequestDTO profileAvatarUpdateRequestDTO){
        //load profile entity
        Profile profile = getProfileById(profileId);
        if(profile != null){
            //get profile avatar blob entity
            //TODO : NEEDS WORK !! THIS NEEDS TO BE REFACTORED AS WE CHANGED THE PROFILE - BLOB RELATIONSHIP
//            BlobEntity avatarBlobEntity = profile.getAvatar();
//            if(avatarBlobEntity != null){
//                avatarBlobEntity = profile.getAvatar();
//            }else{
//                //new avatar
//                avatarBlobEntity = new BlobEntity();
//                avatarBlobEntity.setProfile(profile);
//            }
//            //update the blob information
//            avatarBlobEntity.setType(EBlobType.valueOf(profileAvatarUpdateRequestDTO.getAvatarType()));
//            avatarBlobEntity.setPath(profileAvatarUpdateRequestDTO.getAvatarUrl());
//            avatarBlobEntity.setName(profileAvatarUpdateRequestDTO.getAvatarName());
//            avatarBlobEntity.setSize(profileAvatarUpdateRequestDTO.getAvatarSize());
//            avatarBlobEntity.setTimestamp(new Date().toString());
//            //save
//            blobEntityService.save(avatarBlobEntity);
            return true;
        }
        return false;
    }

    public boolean activateProfile(String profileId){
        logger.info("ProfileService:activateProfile::Starting activating user profile with profileId = " + profileId);
        Profile profile = getProfileById(profileId);
        if(profile != null){
            profile.setActivated(true);
            profileRepository.save(profile);
            logger.info("ProfileService:activateProfile::profile with id = %s was activated successfully" + profileId);
            return true;
        }
        logger.info("ProfileService:activateProfile::profile with id = %s was not found" + profileId);
        return false;
    }

    public boolean blockProfile(String profileId){
        logger.info("ProfileService:blockProfile::Starting blocking user profile with profileId = " + profileId);
        Profile profile = getProfileById(profileId);
        if(profile != null){
            profile.setBlocked(true);
            profileRepository.save(profile);
            logger.info("ProfileService:blockProfile::profile with id = %s was blocked successfully" + profileId);
            return true;
        }
        logger.info("ProfileService:blockProfile::profile with id = %s was not found" + profileId);
        return false;
    }

//    public boolean unBlockProfile(String profileId){
//        logger.info("ProfileService:unBlockProfile::Starting unblocking user profile with profileId = " + profileId);
//        Profile profile = getProfileById(profileId);
//        if(profile != null){
//            profile.setBlocked(false);
//            profileRepository.save(profile);
//            logger.info("ProfileService:unBlockProfile::profile with id = %s was unblocking successfully" + profileId);
//            return true;
//        }
//        logger.info("ProfileService:unBlockProfile::profile with id = %s was not found" + profileId);
//        return false;
//    }
    public Profile getUserProfile(User user){
       Optional<Profile> profileOptional =  this.profileRepository.findByUser(user);
       if(profileOptional.isPresent()){
           return profileOptional.get();
       }
       return null;
    }
}
