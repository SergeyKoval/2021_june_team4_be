package com.exadel.discount.config;

import com.exadel.discount.controller.AuthenticationController;
import com.exadel.discount.model.entity.City;
import com.exadel.discount.model.entity.Role;
import com.exadel.discount.model.entity.User;
import com.exadel.discount.security.config.SecurityConfig;
import com.exadel.discount.service.impl.JwtServiceImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class UserSecurityContext implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser withMockCustomUser) {
        User user = new User();
        user.setId(withMockCustomUser.UUID2);
        user.setFirstName(withMockCustomUser.firstName());
        user.setLastName(withMockCustomUser.lastName());
        user.setEmail(withMockCustomUser.email());
        user.setPassword(withMockCustomUser.password());
        user.setLogin(withMockCustomUser.login());
        user.setPhone(withMockCustomUser.phone());
        user.setRole(Role.valueOf(withMockCustomUser.role()));

        SecurityContext context = SecurityContextHolder.createEmptyContext();


        return context;
    }
}
