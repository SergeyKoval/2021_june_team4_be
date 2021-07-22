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
public class VendorLocationMockIT extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getVendorLocationByIdAdmin() throws Exception {
        mockMvc.perform(get("/locations/{id}", UUID.fromString
                ("bb682ec1-c86a-4b64-b306-53346c189aca"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser
    void getVendorLocationByIdUser() throws Exception {
        mockMvc.perform(get("/locations/{id}", UUID.fromString
                ("bb682ec1-c86a-4b64-b306-53346c189aca"))
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
                        "    \"vendorId\": \"d4ef9391-bc48-4ba4-b0c7-81896634f611\",\n" +
                        "    \"cityId\":\"794a4106-ff4d-44bb-960a-3dec50b033ab\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.latitude").value("56.3453347347"))
                .andExpect(jsonPath("$.city.id").value("794a4106-ff4d-44bb-960a-3dec50b033ab"));
    }

    @Test
    @WithMockUser
    void addNewLocationUser() throws Exception {
        mockMvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"latitude\":\"56.3453347347\",\n" +
                        "    \"longitude\":\"23.65456456\",\n" +
                        "    \"vendorId\": \"d4ef9391-bc48-4ba4-b0c7-81896634f611\",\n" +
                        "    \"cityId\":\"794a4106-ff4d-44bb-960a-3dec50b033ab\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser
    void deleteLocationUser() throws Exception {
        mockMvc.perform(delete("/locations/{id}", UUID.fromString("6089a6fb-572b-4f29-a2c6-46ac0a5fbca5"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteLocationAdmin() throws Exception {
        mockMvc.perform(delete("/locations/{id}", UUID.fromString("6089a6fb-572b-4f29-a2c6-46ac0a5fbca5"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateLocationUser() throws Exception {
        mockMvc.perform(put("/locations/{id}", UUID.fromString("9804fd1d-1bd8-4d86-a23d-d808ddcd5525"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"latitude\":\"56.3453347347\",\n" +
                        "    \"longitude\":\"28.65456456\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser
    void updateLocationAdmin() throws Exception {
        mockMvc.perform(put("/locations/{id}", UUID.fromString("9804fd1d-1bd8-4d86-a23d-d808ddcd5525"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"latitude\":\"56.3453347347\",\n" +
                        "    \"longitude\":\"28.65456456\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }
}
