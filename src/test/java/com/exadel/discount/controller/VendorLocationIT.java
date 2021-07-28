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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class VendorLocationIT extends AbstractIT {

    private final UUID location_id = UUID.fromString("9804fd1d-1bd8-4d86-a23d-d808ddcd5525");
    private final String vendor_id = "3633f3cf-7208-4d67-923d-ce6b2cec29e2";
    private final String city_id = "c6a9f8a9-0494-4f51-9c2a-189307ad0cfd";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getVendorLocationByIdAdmin() throws Exception {
        mockMvc.perform(get("/locations/{id}", location_id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser
    void getVendorLocationByIdUser() throws Exception {
        mockMvc.perform(get("/locations/{id}", location_id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void addNewLocationAdmin() throws Exception {
        mockMvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"latitude\":\"56.3453347347\",\n" +
                        "    \"longitude\":\"23.65456456\",\n" +
                        "    \"vendorId\": \"" + vendor_id + "\",\n" +
                        "    \"cityId\":\"" + city_id + "\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.latitude").value("56.3453347347"))
                .andExpect(jsonPath("$.city.id").value(city_id));
    }

    @Test
    @WithMockUser
    void addNewLocationUser() throws Exception {
        mockMvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"latitude\":\"56.3453347347\",\n" +
                        "    \"longitude\":\"23.65456456\",\n" +
                        "    \"vendorId\": \"" + vendor_id + "\",\n" +
                        "    \"cityId\":\"" + city_id + "\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser
    void deleteLocationUser() throws Exception {
        mockMvc.perform(delete("/locations/{id}", location_id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteLocationAdmin() throws Exception {
        mockMvc.perform(delete("/locations/{id}", location_id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateLocationAdmin() throws Exception {
        mockMvc.perform(put("/locations/{id}", location_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"latitude\":\"56.3453347347\",\n" +
                        "    \"longitude\":\"28.65456456\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @WithMockUser
    void updateLocationUser() throws Exception {
        mockMvc.perform(put("/locations/{id}", location_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"latitude\":\"56.3453347347\",\n" +
                        "    \"longitude\":\"28.65456456\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }
}
