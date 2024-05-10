package com.mbbm.app.security.userDetails;

import com.mbbm.app.model.base.User;
import com.mbbm.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    //-------------------------- FOR TESTING ONLY ----------------------------------------
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("javainuse".equals(username)) {
//            User user =  new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//            return UserDetailsImpl.build(user);
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//    }

    //-------------------------- ACTUAL IMPLEMENTATION ------------------------------------
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return UserDetailsImpl.build(user.get());
    }
}