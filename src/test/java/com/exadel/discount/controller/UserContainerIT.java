package com.exadel.discount.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class UserContainerIT extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    @Test
    public void getAllFilteredUsersIT() throws Exception {
        mockMvc.perform(get("/users")
                .param("cityIds", "c6a9f8a9-0494-4f51-9c2a-189307ad0cfd")
                .param("countryIds", "b49abef5-83fe-4d0c-9927-c0aaaf49a2b7")
                .param("firstName", "Ivan")
                .param("lastName", "Ivanov")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].firstName").value("Ivan"))
                .andExpect(jsonPath("$[0].lastName").value("Ivanov"))
                .andExpect(jsonPath("$[0].city.name").value("Kyiv"));
    }

    @WithMockUser
    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/users/91cf19dd-2af7-49ee-825e-94c0831ba1f2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value("Ivan"));
    }
}