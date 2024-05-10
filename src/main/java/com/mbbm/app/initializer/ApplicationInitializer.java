package com.mbbm.app.initializer;

import com.mbbm.app.service.RoleService;
import com.mbbm.app.service.SubscriptionService;
import com.mbbm.app.service.authentication.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationInitializer {

    private final RoleService roleService;
    private final UserRegistrationService userRegistrationService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public ApplicationInitializer(RoleService roleService,
                                  UserRegistrationService userRegistrationService,
                                  SubscriptionService subscriptionService){
        this.roleService = roleService;
        this.userRegistrationService = userRegistrationService;
        this.subscriptionService = subscriptionService;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try{
            roleService.initApplicationRoles();
            subscriptionService.initApplicationSubscriptions();
            userRegistrationService.registerSuperAdminUser();
        }catch (Exception exception){
            //TODO : HADNLE EXCEPTION PROPERLY
            System.out.println(Arrays.toString(exception.getStackTrace()));
        }
    }
}
