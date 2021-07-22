package com.exadel.discount.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiscountContainIT extends AbstractIT {

    @Autowired
    WebApplicationContext wac;

    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    @Test
    void addDiscount() throws Exception {
        given()
                .webAppContextSetup(wac)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/discounts/{id}", UUID.fromString("5f69268b-705e-4fb9-8147-722b4ec1d9da"))
                .then()
                .status(HttpStatus.OK)
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful());
        //  .assertThat(jsonPath("$.name").value("Discount on yoga weekdays"))
        // .assertThat(jsonPath("$.vendor.id").value("3633f3cf-7208-4d67-923d-ce6b2cec29e2"));

    }
}