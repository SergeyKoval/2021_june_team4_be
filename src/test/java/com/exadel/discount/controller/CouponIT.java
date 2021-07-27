package com.exadel.discount.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql("classpath:testdata/favorite_add_test_data.sql")
@WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CouponIT extends AbstractIT {

    @Autowired
    WebApplicationContext wac;

    @Test
    public void getAllUsersCouponsIT() throws Exception {

        given().webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("userId", "91cf19dd-2af7-49ee-825e-94c0831ba1f1")
                .when()
                .get("/coupons")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$[0].discount.name").value("Toy for your dog"))
                .assertThat(jsonPath("$[0].discount.description").value("Dog stuff"))
                .assertThat(jsonPath("$[0].discount.value").value("5"))
                .assertThat(jsonPath("$[0].discount.discountType").value("PERCENT"))
                .assertThat(jsonPath("$[0].discount.promo").value("1abcde1"))
                .assertThat(jsonPath("$[0].discount.endTime").value("2021-07-20T00:00:00"))
                .assertThat(jsonPath("$[0].discount.archived").value("false"))
                .assertThat(jsonPath("$[0].discount.vendor.name").value("Dog stuff"))
                .assertThat(jsonPath("$[0].discount.category.name").value("Dogs"));
    }

    @Test
    public void getCouponByIdIT() throws Exception {
        given().webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/coupons/93577f24-f68f-403e-aa04-0a60c3a4a1c3")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.id").value("93577f24-f68f-403e-aa04-0a60c3a4a1c3"))
                .assertThat(jsonPath("$.discount.name").value("Toy for your dog"))
                .assertThat(jsonPath("$.discount.description").value("Dog stuff"))
                .assertThat(jsonPath("$.discount.value").value("5"))
                .assertThat(jsonPath("$.discount.discountType").value("PERCENT"))
                .assertThat(jsonPath("$.discount.promo").value("1abcde1"))
                .assertThat(jsonPath("$.discount.endTime").value("2021-07-20T00:00:00"))
                .assertThat(jsonPath("$.discount.archived").value("false"))
                .assertThat(jsonPath("$.discount.vendor.name").value("Dog stuff"))
                .assertThat(jsonPath("$.discount.category.name").value("Dogs"));
    }

    @Test
    public void getNotExistCouponByIdIT() throws Exception {
        given().webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/coupons/93577f24-f68f-403e-aa04-0a60c3a4a1c9")
                .then()
                .assertThat(MockMvcResultMatchers.status().is4xxClientError())
                .assertThat(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getSearchedCouponIT() throws Exception {
        given().webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("searchText", "Toy for")
                .when()
                .get("/coupons/search")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$[0].discount.name").value("Toy for your dog"))
                .assertThat(jsonPath("$[0].discount.description").value("Dog stuff"))
                .assertThat(jsonPath("$[0].discount.value").value("5"))
                .assertThat(jsonPath("$[0].discount.discountType").value("PERCENT"))
                .assertThat(jsonPath("$[0].discount.promo").value("1abcde1"))
                .assertThat(jsonPath("$[0].discount.endTime").value("2021-07-20T00:00:00"))
                .assertThat(jsonPath("$[0].discount.archived").value("false"))
                .assertThat(jsonPath("$[0].discount.vendor.name").value("Dog stuff"))
                .assertThat(jsonPath("$[0].discount.category.name").value("Dogs"));
    }
}
