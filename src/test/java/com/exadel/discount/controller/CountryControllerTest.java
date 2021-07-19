package com.exadel.discount.controller;

import com.exadel.discount.model.entity.Country;
import com.exadel.discount.service.CityService;
import com.exadel.discount.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@ActiveProfiles("test")
public class CountryControllerTest {
    private static final UUID ID = UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6");
    private static final Country country;

    static {
        country = new Country();
        country.setName("Test");
        country.setId(ID);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @MockBean
    private CityService cityService;

    @Test
    public void getAllCountriesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void getCountryByIdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/countries/971bf698-f3ea-4a97-85e8-0a2a770736d6"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void saveCountryTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\"" + "}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void deleteCountryByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/countries/971bf698-f3ea-4a97-85e8-0a2a770736d6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void getAllCitiesByCountryIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/countries/5a009936-ac14-4b4b-9121-3638122ea6b5/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void saveCityTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/countries/5a009936-ac14-4b4b-9121-3638122ea6b5/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\"" + "}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void getCityByIdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/countries/971bf698-f3ea-4a97-85e8-0a2a770736d6/cities/5a009936-ac14-4b4b-9121-3638122ea6b5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void deleteCityTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/countries/971bf698-f3ea-4a97-85e8-0a2a770736d6/cities/5a009936-ac14-4b4b-9121-3638122ea6b5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
