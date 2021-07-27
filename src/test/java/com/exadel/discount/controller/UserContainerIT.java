package com.exadel.discount.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
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
@Sql("classpath:testdata/user_add_test_data.sql")
@WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserContainerIT extends AbstractIT {

    @Autowired
    WebApplicationContext wac;

    @Test
    public void getAllFilteredUsersIT() throws Exception {
        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("cityIds", "93577f24-f68f-403e-aa04-0a60c3a445d8")
                .param("countryIds", "93577f24-f68f-403e-aa04-0a60c3a445d7")
                .param("firstName", "Andrey")
                .param("lastName", "Andreyev")
                .when()
                .get("/users")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$[0].firstName").value("Andrey"))
                .assertThat(jsonPath("$[0].lastName").value("Andreyev"))
                .assertThat(jsonPath("$[0].email").value("Andreyev@gmail.com"))
                .assertThat(jsonPath("$[0].phone").value("+380501113337"))
                .assertThat(jsonPath("$[0].role").value("USER"))
                .assertThat(jsonPath("$[0].city.countryName").value("Ukraine"))
                .assertThat(jsonPath("$[0].city.name").value("Kyiv"));
    }

    @WithMockUser
    @Test
    void getUserById() throws Exception {
        given().
                webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/users/91cf19dd-2af7-49ee-825e-94c0831ba1f1")
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk())
                .assertThat(MockMvcResultMatchers.status().is2xxSuccessful())
                .assertThat(jsonPath("$.firstName").value("Andrey"))
                .assertThat(jsonPath("$.lastName").value("Andreyev"))
                .assertThat(jsonPath("$.email").value("Andreyev@gmail.com"))
                .assertThat(jsonPath("$.phone").value("+380501113337"))
                .assertThat(jsonPath("$.role").value("USER"))
                .assertThat(jsonPath("$.city.name").value("Kyiv"));
    }

    @Test
    public void getNotExistUserIT() throws Exception {
        given()
                .webAppContextSetup(wac)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("cityIds", "93577f24-f68f-403e-aa04-0a60c3a445d8")
                .param("countryIds", "93577f24-f68f-403e-aa04-0a60c3a445d7")
                .param("firstName", "Ivan")
                .param("lastName", "Ivanov")
                .when()
                .get("/users/91cf19dd-2af7-49ee-825e-94c0831ba1")
                .then()
                .assertThat(MockMvcResultMatchers.status().isNotFound())
                .assertThat(MockMvcResultMatchers.status().is4xxClientError());
    }
}
