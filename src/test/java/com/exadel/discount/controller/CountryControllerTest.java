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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class CountryControllerTest extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetAllCountriesForAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetCountryById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testSaveCountry() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\"" + "}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testDeleteCountryById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/countries/ec62f606-7de5-443a-8d37-cee82134c6cf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetAllCitiesByCountryId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testSaveCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\"" + "}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testGetCityById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7/cities/489cd7f8-870c-4dd1-abc8-e31145a15c5c"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void testDeleteCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7/cities/658b74f2-4fb5-4b9d-a87f-0b8f44f06f09")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
