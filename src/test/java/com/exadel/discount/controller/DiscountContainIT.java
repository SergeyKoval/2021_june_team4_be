package com.exadel.discount.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.comparesEqualTo;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DiscountContainIT extends AbstractIT {

    @Autowired
    WebApplicationContext wac;

  //  @Sql("classpath:/test_db.sql")
    @WithMockUser//(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    @Test
    void findLoadedDiscount() throws Exception {
        given()
                .webAppContextSetup(wac)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/discounts/{id}", UUID.fromString("5f69268b-705e-4fb9-8147-722b4ec1d9da"))
                .then()
                .status(HttpStatus.OK)
                .body(
                        "discount.name", comparesEqualTo("Discount on yoga weekdays"),
                        "discount.promo", comparesEqualTo("1abcde1")
                );
    }
}