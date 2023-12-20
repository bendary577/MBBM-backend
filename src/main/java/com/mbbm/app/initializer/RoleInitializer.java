package com.mbbm.app.initializer;

import com.mbbm.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author mohamed.bendary
 * this class is used to initialize the application configured user roles in database
 * this configured roles will be then loaded in several components in the app to be used
 */
@Component
public class RoleInitializer {

    @Autowired
    private RoleService roleService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleService.initApplicationRoles();
    }
}
