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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@ActiveProfiles("integrationtest")
public class CountryControllerTest extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllCountriesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void getCountryByIdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void saveCountryTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\"" + "}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Test"));
    }

   /* @Test
    public void deleteCountryByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/countries/ec62f606-7de5-443a-8d37-cee82134c6cf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }*/

    @Test
    public void getAllCitiesByCountryIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void saveCityTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\"" + "}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    public void getCityByIdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/countries/b49abef5-83fe-4d0c-9927-c0aaaf49a2b7/cities/489cd7f8-870c-4dd1-abc8-e31145a15c5c"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

   /* @Test
    public void deleteCityTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/countries/13e43451-6b1b-4e0f-bc61-8d903d226617/cities/794a4106-ff4d-44bb-960a-3dec50b033ab")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }*/
}
