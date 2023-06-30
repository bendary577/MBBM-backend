package com.mbbm.app.service;

import com.mbbm.app.http.response.messages.ResponseMessages;
import com.mbbm.app.security.jwt.JwtTokenUtil;
import com.mbbm.app.security.userDetails.UserDetailsImpl;
import com.mbbm.app.security.userDetails.UserDetailsServiceImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public UserDetails buildUserDetailsObjectUsingUsername(String username){
        return userDetailsService
                .loadUserByUsername(username);
    }

    public JSONObject buildLoginResponse(UserDetailsImpl userDetailsObject, UserDetails userDetails){
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).toList();

        Map<String , Object> loginInfo = new HashMap<>();
        loginInfo.put("userId", userDetailsObject.getUserId());
        loginInfo.put("username", userDetailsObject.getUsername());
        loginInfo.put("email", userDetailsObject.getEmail());
        loginInfo.put("roles", roles);
        loginInfo.put("token", jwtTokenUtil.generateToken(userDetails));

        JSONObject loginResponse = new JSONObject();
        loginResponse.put("message", ResponseMessages.SUCCESSFUL_LOGIN);
        loginResponse.put("data", loginInfo);
        return loginResponse;
    }

}
