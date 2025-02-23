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

    private static Employee currentUser; 

    public static void setCurrentUser(Employee user) {
        currentUser = user;
    }

    public static void clearCurrentUser() { 
        currentUser = null;
    }

    public static Employee getCurrentUser() { 
        return currentUser;
    }

    @Pointcut("execution(* com.example.employeetaskmanager.utils.Menus.adminMenu(..))")
    public void adminMethods() {}

    @Before("adminMethods()")
    public void checkAdminAccess() {
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            throw new SecurityException("🚨 Access Denied! Admin rights required.");
        }
    }
}
