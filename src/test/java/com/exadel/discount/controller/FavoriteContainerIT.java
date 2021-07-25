package com.exadel.discount.controller;

import com.exadel.discount.model.entity.Favorite;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.FavoriteRepository;
import com.exadel.discount.repository.UserRepository;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class FavoriteContainerIT extends AbstractIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    DiscountRepository discountRepository;

    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    @Test
    public void postNewCouponAndGetByFiltersAndSearchingAndByIdIT() throws Exception {
        String userId = userRepository.findByEmail("admin@mail.com").get().getId().toString();

        mockMvc.perform(post("/favorites")
                .param("discountId", "779c30b2-06f9-43d1-87e0-edf4a31784f4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.discount.id").value("779c30b2-06f9-43d1-87e0-edf4a31784f4"));

        mockMvc.perform(get("/favorites")
                .param("vendorId", "3633f3cf-7208-4d67-923d-ce6b2cec29e2")
                .param("categoryIds", "5a009936-ac14-4b4b-9121-3638122ea6b5")
                .param("cityId", "c6a9f8a9-0494-4f51-9c2a-189307ad0cfd")
                .param("countryId", "b49abef5-83fe-4d0c-9927-c0aaaf49a2b7")
                .param("userId", userId)
                .param("tags", "b28349b5-0b39-45ee-bb3c-4f96c1abfe75")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].discount.name").value("Discount on all sports weekends"))
                .andExpect(jsonPath("$[0].discount.vendor.name").value("SportLife"))
                .andExpect(jsonPath("$[0].discount.category.name").value("Sport"));

        mockMvc.perform(get("/favorites/search")
                .param("size", "10")
                .param("searchText", "Discount on all sports weekends")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].discount.name").value("Discount on all sports weekends"))
                .andExpect(jsonPath("$[0].discount.vendor.name").value("SportLife"))
                .andExpect(jsonPath("$[0].discount.category.name").value("Sport"));

        //Filtering all favorites DESC by userId and couponId to find last created favoriteId as arg for findById test
        List<Favorite> favoriteList = favoriteRepository.findAll().stream()
                .filter(favorite -> favorite.getUser().getId().equals(UUID.fromString(userId)))
                .filter(favorite -> favorite.getDiscount().getId().equals(UUID
                        .fromString("779c30b2-06f9-43d1-87e0-edf4a31784f4")))
                .collect(Collectors.toList());
        String favoriteId = favoriteList.get(0).getId().toString();

        mockMvc.perform(get("/favorites/{favoriteId}", favoriteId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(favoriteId))
                .andExpect(jsonPath("$.discount.name").value("Discount on all sports weekends"))
                .andExpect(jsonPath("$.discount.vendor.name").value("SportLife"))
                .andExpect(jsonPath("$.discount.category.name").value("Sport"));
    }
}
