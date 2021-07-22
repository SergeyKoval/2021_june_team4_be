package com.exadel.discount.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@Sql("classpath:test_db.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiscountContainIT extends AbstractIT {

    @Autowired
    WebApplicationContext wac;

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void addDiscountIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth().with(SecurityMockMvcRequestPostProcessors.user("admin@mail.com").roles("ADMIN"))
                .contentType("application/json")
                .body("{\"name\": \"Discount on tennis weekdays\", " +
                        "\"promo\": \"11aa3g7\"," +
                        "\"discountType\": \"PERCENT\"," +
                        "\"value\":\"10\"," +
                        "\"categoryId\": \"5a009936-ac14-4b4b-9121-3638122ea6b5\"," +
                        "\"vendorId\": \"3633f3cf-7208-4d67-923d-ce6b2cec29e2\", " +
                        "\"vendorLocationsIds\": [\"bb682ec1-c86a-4b64-b306-53346c189aca\", " +
                        "\"6089a6fb-572b-4f29-a2c6-46ac0a5fbca5\"]," +
                        "\"tagIds\": [\"537edc43-1616-4622-bf45-7b060e6d6471\"]}")
                .when()
                .post("/discounts")
                .then()
                // .statusCode(201)
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.name").value("Discount on tennis weekdays"))
                .assertThat(jsonPath("$.vendor.id").value("3633f3cf-7208-4d67-923d-ce6b2cec29e2"));
    }

    @Test
    @WithMockUser
    public void retrieveDiscountByIdIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/discounts/{id}", UUID.fromString("93577f24-f68f-403e-aa04-0a60c3a445d1"))
                .then()
                .status(HttpStatus.OK)
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.name").value("Toy for your dog"))
                .assertThat(jsonPath("$.vendor.name").value("Dog stuff"));
    }
}