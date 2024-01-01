package com.mbbm.app.aop;

import com.mbbm.app.multitenant.TenantContext;
import com.mbbm.app.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAspect {

    //TODO:REVISE MULTITENANT SOLUTION
    @Before("execution(* com.mbbm.app.service.UserService.*(..))&& target(userService)")
    public void aroundExecution(JoinPoint joinPoint, UserService userService) throws Throwable {
//        org.hibernate.Filter filter = userService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
//        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
//        filter.validate();
    }
}
