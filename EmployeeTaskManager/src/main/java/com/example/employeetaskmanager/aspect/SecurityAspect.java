package com.example.employeetaskmanager.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.example.employeetaskmanager.model.Role;
import com.example.employeetaskmanager.model.Employee;

@Aspect
@Component
public class SecurityAspect {

    private Employee currentUser;
    
    public void setCurrentUser(Employee user) {
        this.currentUser = user;
    }

    @Pointcut("execution(* com.example.employeetaskmanager.utils.Menus.adminMenu(..))")
    public void adminMethods() {}

    @Before("adminMethods()")
    public void checkAdminAccess() {
        if (currentUser == null || !currentUser.getRole().equals(Role.ADMIN)) {
            throw new SecurityException("Access Denied! Admin rights required.");
        }
    }
}
