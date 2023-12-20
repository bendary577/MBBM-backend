package com.mbbm.app.service;

import com.mbbm.app.enums.ERole;
import com.mbbm.app.model.base.Role;
import com.mbbm.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(ERole roleName){
        Optional<Role> role =  roleRepository.findByName(roleName);
        return role.isPresent() ? role.get() : null;
    }

    public Set<Role> generateRolesListForNewUser(int userType){
        Set<Role> roles = new HashSet<>();
        ERole role = ERole.valueOf(userType);
        switch(role){
            case ROLE_ADMIN:
                Role adminRole = findByName(ERole.ROLE_ADMIN);
                roles.add(adminRole);
                break;
            case ROLE_MODERATOR:
                Role moderatorRole = findByName(ERole.ROLE_MODERATOR);
                roles.add(moderatorRole);
                break;
            case ROLE_MEDIA_BUYER:
                Role mediaBuyerRole = findByName(ERole.ROLE_MEDIA_BUYER);
                roles.add(mediaBuyerRole);
                break;
            default:
                //TODO :: handle this exception properly
                System.out.println("throw an exception");
        }
        return roles;
    }

    public void initApplicationRoles(){
        for(ERole eRole : ERole.values()){
            if(findByName(eRole)==null){
                Role role = new Role();
                role.setName(eRole);
                save(role);
            }
        }
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}
