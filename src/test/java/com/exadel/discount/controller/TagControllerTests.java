package com.exadel.discount.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class TagControllerTests extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetAllTags() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tags"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value("3a7d3fb1-c694-4d8a-9a78-176864405cdc"))
                .andExpect(jsonPath("$[0].name").value("Pizza"))
                .andExpect(jsonPath("$[1].id").value("452e5fc0-100a-41e9-951d-ba51f06a795e"))
                .andExpect(jsonPath("$[1].name").value("Sushi"))
                .andExpect(jsonPath("$[2].id").value("bf48f42a-8110-42fd-b989-df90ed24c56e"))
                .andExpect(jsonPath("$[2].name").value("Nails"))
                .andExpect(jsonPath("$[3].id").value("46868e13-ea9d-42d1-8811-29413b1d1763"))
                .andExpect(jsonPath("$[3].name").value("Makeup"))
                .andExpect(jsonPath("$[4].id").value("537edc43-1616-4622-bf45-7b060e6d6471"))
                .andExpect(jsonPath("$[4].name").value("Fitness"))
                .andExpect(jsonPath("$[5].id").value("b28349b5-0b39-45ee-bb3c-4f96c1abfe75"))
                .andExpect(jsonPath("$[5].name").value("Yoga"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testSaveTag() throws Exception {
        mockMvc.perform(post("/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\"" + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testSaveTagException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tags")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetTagById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/tags/b28349b5-0b39-45ee-bb3c-4f96c1abfe75"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testDeleteTag() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/tags/452e5fc0-100a-41e9-951d-ba51f06a795e")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
