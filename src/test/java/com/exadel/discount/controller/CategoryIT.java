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
@Sql("classpath:testdata/category_add_test_data.sql")
@WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIT extends AbstractIT {

    @Autowired
    WebApplicationContext wac;

    @Test
    @Order(1)
    public void retrieveCategoryByIdIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/categories/93577f24-f68f-403e-aa04-0a60c3a445d6")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.name").value("Dogs"))
                .assertThat(jsonPath("$.id").value("93577f24-f68f-403e-aa04-0a60c3a445d6"));
    }

    @Test
    @Order(2)
    public void retrieveNotExistCategoryIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/categories/93577f24-f68f-403e-aa04-0a60c3a445d7")
                .then()
                .assertThat(MockMvcResultMatchers.status().isNotFound())
                .assertThat(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(3)
    public void retrieveAllCategoriesIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/categories")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Order(4)
    public void createCategoryIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"New category\"}")
                .when()
                .post("/categories")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.name").value("New category"));
    }

    @Test
    @Order(5)
    public void updateCategoryIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\": \"Dog toys\"}")
                .when()
                .put("/categories/93577f24-f68f-403e-aa04-0a60c3a445d6")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.name").value("Dog toys"))
                .assertThat(jsonPath("$.id").value("93577f24-f68f-403e-aa04-0a60c3a445d6"));
    }

    @Test
    @Order(6)
    public void deleteCategoryIT() throws Exception {

        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete("/categories/93577f24-f68f-403e-aa04-0a60c3a445d6")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
