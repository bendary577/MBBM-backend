package com.mbbm.app.service.authentication;

import com.mbbm.app.model.base.Profile;
import com.mbbm.app.model.base.User;
import com.mbbm.app.service.ProfileService;
import com.mbbm.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

   public String getCurrentAuthenticatedUsername(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
           return authentication.getName();
       }
       return "";
   }

   public Profile getCurrentAuthenticatedUserProfile(){
       Profile profile = null;
       String username = getCurrentAuthenticatedUsername();
       if(!username.isEmpty()){
          User user =  userService.getUserByUserName(username);
          if(user != null){
              profile = profileService.getUserProfile(user);
          }
       }
       return profile;
   }

}
