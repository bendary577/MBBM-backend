package com.mbbm.app.service;

import com.mbbm.app.enums.ERole;
import com.mbbm.app.model.base.Role;
import com.mbbm.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(ERole roleName){
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}
