package com.exadel.discount.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DiscountContainerIT extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    public void addDiscountIT() throws Exception {

        mockMvc.perform(post("/discounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Discount on yoga weekdays\", " +
                        "\"promo\": \"11aa3g7\"," +
                        "\"discountType\": \"PERCENT\"," +
                        "\"value\":\"10\"," +
                        "\"categoryId\": \"5a009936-ac14-4b4b-9121-3638122ea6b5\"," +
                        "\"vendorId\": \"3633f3cf-7208-4d67-923d-ce6b2cec29e2\", " +
                        "\"vendorLocationsIds\": [\"bb682ec1-c86a-4b64-b306-53346c189aca\", " +
                        "\"6089a6fb-572b-4f29-a2c6-46ac0a5fbca5\"]," +
                        "\"tagIds\": [\"537edc43-1616-4622-bf45-7b060e6d6471\"]}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Discount on yoga weekdays"))
                .andExpect(jsonPath("$.vendor.id").value("3633f3cf-7208-4d67-923d-ce6b2cec29e2"));
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    public void removeAndRestoreDiscountIT() throws Exception {
        mockMvc.perform(delete("/discounts/{id}", "5f69268b-705e-4fb9-8147-722b4ec1d9da")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/discounts/{id}", UUID.fromString
                ("5f69268b-705e-4fb9-8147-722b4ec1d9da"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));

        mockMvc.perform(put("/discounts/archived/{id}/restore", "5f69268b-705e-4fb9-8147-722b4ec1d9da")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.archived").value(false))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    public void getArchivedAfterDeletionIT() throws Exception {
        UUID discountId = UUID.fromString("5f69268b-705e-4fb9-8147-722b4ec1d9da");
        String categoryId = UUID.fromString("5a009936-ac14-4b4b-9121-3638122ea6b5").toString();
        mockMvc.perform(delete("/discounts/{id}", "5f69268b-705e-4fb9-8147-722b4ec1d9da")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(get("/discounts/archived")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].category.id").value(categoryId))
                .andExpect(jsonPath("$[0].archived").value(true))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @WithMockUser
    @Test
    void getDiscountById() throws Exception {
        mockMvc.perform(get("/discounts/{id}", "5f69268b-705e-4fb9-8147-722b4ec1d9da")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @WithMockUser
    @Test
    public void getAllDiscounts_200IT() throws Exception {
        mockMvc.perform(get("/discounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void getDiscountsFilteredByCategoryIdIT() throws Exception {
        mockMvc.perform(get("/discounts")
                .param("categoryId", "5a009936-ac14-4b4b-9121-3638122ea6b5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].category.id").value("5a009936-ac14-4b4b-9121-3638122ea6b5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @WithMockUser
    @Test
    public void getDiscountSearchingIT() throws Exception {
        mockMvc.perform(get("/discounts/search")
                .param("size", "3")
                .param("searchText", "sport")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].category.name").value("Sport"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}