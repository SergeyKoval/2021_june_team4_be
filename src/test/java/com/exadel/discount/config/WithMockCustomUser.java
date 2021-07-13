package com.exadel.discount.config;

import com.exadel.discount.model.entity.City;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = UserSecurityContext.class)
public @interface WithMockCustomUser {
    UUID UUID2 = UUID.fromString("74419b00-bd86-42cb-980c-0aa970052b87");
    UUID  getUuid2 =  UUID2;
    String firstName() default "Person";
    String lastName() default "Person";
    String phone() default "380";
    String login() default "admin@mail.com";
    String email() default "admin@mail.com";
    String password() default "admin";
    String role() default "ADMIN";
}
