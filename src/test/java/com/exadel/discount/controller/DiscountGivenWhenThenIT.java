package com.exadel.discount.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql("classpath:testdata/discount_add_test_data.sql")
@WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiscountGivenWhenThenIT extends AbstractIT {

    @Autowired
    WebApplicationContext wac;

    @Order(1)
    @Test
    void addDiscountIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .contentType("application/json")
                .body("{\"name\": \"Discount on tennis weekdays\", " +
                        "\"promo\": \"11aa3g7\"," +
                        "\"discountType\": \"PERCENT\"," +
                        "\"value\":\"10\"," +
                        "\"categoryId\": \"93577f24-f68f-403e-aa04-0a60c3a445d6\"," +
                        "\"vendorId\": \"93577f24-f68f-403e-aa04-0a60c3a445d2\", " +
                        "\"vendorLocationsIds\": [\"93577f24-f68f-403e-aa04-0a60c3a445d3\", " +
                        "\"93577f24-f68f-403e-aa04-0a60c3a445d4\"]," +
                        "\"tagIds\": [\"93577f24-f68f-403e-aa04-0a60c3a445d5\"]}")
                .when()
                .post("/discounts")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.name").value("Discount on tennis weekdays"))
                .assertThat(jsonPath("$.promo").value("11aa3g7"))
                .assertThat(jsonPath("$.discountType").value("PERCENT"))
                .assertThat(jsonPath("$.category.id").value("93577f24-f68f-403e-aa04-0a60c3a445d6"))
                .assertThat(jsonPath("$.tagIds[0].id").value("93577f24-f68f-403e-aa04-0a60c3a445d5"))
                .assertThat(jsonPath("$.vendor.id").value("93577f24-f68f-403e-aa04-0a60c3a445d2"));
    }

    @Test
    @Order(2)
    public void retrieveDiscountByIdIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/discounts/93577f24-f68f-403e-aa04-0a60c3a445d1")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.name").value("Toy for your dog"))
                .assertThat(jsonPath("$.vendor.name").value("Dog stuff"));
    }

    @Test
    @Order(3)
    void findAllDiscountsIT() throws Exception {
        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/discounts")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Order(4)
    void findAllDiscountsWithFiltersIT() throws Exception {
        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("categoryId", "93577f24-f68f-403e-aa04-0a60c3a445d6")
                .param("vendorId", "93577f24-f68f-403e-aa04-0a60c3a445d2")
                .param("vendorLocationsIds", "93577f24-f68f-403e-aa04-0a60c3a445d3",
                        "93577f24-f68f-403e-aa04-0a60c3a445d4")
                .param("tagIds", "93577f24-f68f-403e-aa04-0a60c3a445d65")
                .param("LocalDateTime endDateTimeFrom", "2021-07-19T23:50:00")
                .param("LocalDateTime endDateTimeTo", "2021-07-20T00:10:00")
                .when()
                .get("/discounts")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$[0].name").value("Toy for your dog"))
                .assertThat(jsonPath("$[0].description").value("Dog stuff"))
                .assertThat(jsonPath("$[0].value").value("5"))
                .assertThat(jsonPath("$[0].discountType").value("PERCENT"))
                .assertThat(jsonPath("$[0].promo").value("1abcde1"))
                .assertThat(jsonPath("$[0].endTime").value("2021-07-20T00:00:00"))
                .assertThat(jsonPath("$[0].archived").value("false"))
                .assertThat(jsonPath("$[0].vendor.name").value("Dog stuff"))
                .assertThat(jsonPath("$[0].category.name").value("Dogs"));
    }

    @Test
    @Order(5)
    public void getDiscountSearchingIT() throws Exception {
        given().webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("searchText", "Toy for")
                .when()
                .get("/discounts/search")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$[0].name").value("Toy for your dog"))
                .assertThat(jsonPath("$[0].description").value("Dog stuff"))
                .assertThat(jsonPath("$[0].value").value("5"))
                .assertThat(jsonPath("$[0].discountType").value("PERCENT"))
                .assertThat(jsonPath("$[0].promo").value("1abcde1"))
                .assertThat(jsonPath("$[0].endTime").value("2021-07-20T00:00:00"))
                .assertThat(jsonPath("$[0].archived").value("false"))
                .assertThat(jsonPath("$[0].vendor.name").value("Dog stuff"))
                .assertThat(jsonPath("$[0].category.name").value("Dogs"));
    }

    @Test
    @Order(6)
    public void deleteDiscountByIdtIT() throws Exception {
        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete("/discounts/93577f24-f68f-403e-aa04-0a60c3a445d1")
                .then()
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Order(7)
    public void getDeletedDiscounByIdtIT() throws Exception {
        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/discounts/93577f24-f68f-403e-aa04-0a60c3a445d1")
                .then()
                .assertThat(MockMvcResultMatchers.status().isNotFound())
                .assertThat(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(8)
    @Sql("classpath:testsql/delete_discount.sql")
    public void retrieveArchivedDiscountIT() throws Exception {
        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("categoryId", "93577f24-f68f-403e-aa04-0a60c3a445d6")
                .param("vendorId", "93577f24-f68f-403e-aa04-0a60c3a445d2")
                .param("vendorLocationsIds", "93577f24-f68f-403e-aa04-0a60c3a445d3",
                        "93577f24-f68f-403e-aa04-0a60c3a445d4")
                .param("tagIds", "93577f24-f68f-403e-aa04-0a60c3a445d65")
                .param("LocalDateTime endDateTimeFrom", "2021-07-19T23:50:00")
                .param("LocalDateTime endDateTimeTo", "2021-07-20T00:10:00")
                .when()
                .get("/discounts/archived")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$[0].name").value("Toy for your dog"))
                .assertThat(jsonPath("$[0].description").value("Dog stuff"))
                .assertThat(jsonPath("$[0].value").value("5"))
                .assertThat(jsonPath("$[0].discountType").value("PERCENT"))
                .assertThat(jsonPath("$[0].promo").value("1abcde1"))
                .assertThat(jsonPath("$[0].endTime").value("2021-07-20T00:00:00"))
                .assertThat(jsonPath("$[0].archived").value("true"))
                .assertThat(jsonPath("$[0].vendor.name").value("Dog stuff"))
                .assertThat(jsonPath("$[0].category.name").value("Dogs"));
    }

    @Test
    @Order(9)
    @Sql("classpath:testsql/delete_discount.sql")
    public void deleteDiscountAndCheckIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/discounts/archived/93577f24-f68f-403e-aa04-0a60c3a445d1/restore")
                .then()
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.name").value("Toy for your dog"))
                .assertThat(jsonPath("$.description").value("Dog stuff"))
                .assertThat(jsonPath("$.value").value("5"))
                .assertThat(jsonPath("$.discountType").value("PERCENT"))
                .assertThat(jsonPath("$.promo").value("1abcde1"))
                .assertThat(jsonPath("$.endTime").value("2021-07-20T00:00:00"))
                .assertThat(jsonPath("$.archived").value("false"))
                .assertThat(jsonPath("$.vendor.name").value("Dog stuff"))
                .assertThat(jsonPath("$.category.name").value("Dogs"));
    }
}