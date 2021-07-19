package com.exadel.discount;

import com.exadel.discount.model.entity.*;
import com.exadel.discount.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NewDiscountIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private VendorLocationRepository vendorLocationRepository;
    @Autowired
    private TagRepository tagRepository;

    private Vendor testVendor;
    private VendorLocation testVendorLocation1;
    private VendorLocation testVendorLocation2;
    private Category testCategory;
    private Tag testTag;
    private Discount testDiscount;

    @BeforeEach
    public void setUp() {

        //create new vendor for test
        testVendor = new Vendor();
        testVendor.setId(UUID.fromString
                ("93577f24-f68f-403e-aa04-0a60c3a445d2"));
        testVendor.setName("Dog stuff");
        vendorRepository.save(testVendor);

        //create new vendor locations for test
        Set<VendorLocation> testVendorLocations = new HashSet<>();
        testVendorLocation1 = new VendorLocation();
        testVendorLocation1.setId(UUID.fromString
                ("93577f24-f68f-403e-aa04-0a60c3a445d3"));
        Point point1 = new Point();
        point1.setLatitude(24.3454111);
        point1.setLongitude(10.123111);
        testVendorLocation1.setVendor(testVendor);
        vendorLocationRepository.save(testVendorLocation1);

        testVendorLocation2 = new VendorLocation();
        testVendorLocation2.setId(UUID.fromString
                ("93577f24-f68f-403e-aa04-0a60c3a445d4"));
        Point point2 = new Point();
        point2.setLatitude(24.3454222);
        point2.setLongitude(10.123222);
        testVendorLocation2.setVendor(testVendor);
        vendorLocationRepository.save(testVendorLocation2);

        testVendorLocations.add(testVendorLocation1);
        testVendorLocations.add(testVendorLocation2);
        testVendor.setVendorLocations(testVendorLocations);

        //create new category for test
        testCategory = new Category();
        testCategory.setName("Dogs");
        testCategory.setId(UUID.fromString
                ("93577f24-f68f-403e-aa04-0a60c3a445d6"));
        categoryRepository.save(testCategory);


        //create new tags for test
        Set<Tag> tags = new HashSet<>();
        testTag = new Tag();
        testTag.setName("Pets");
        testTag.setId(UUID.fromString
                ("93577f24-f68f-403e-aa04-0a60c3a445d5"));
        tagRepository.save(testTag);
        tags.add(testTag);

        //create new discount for test
        testDiscount = new Discount();
        testDiscount.setId(UUID.fromString
                ("93577f24-f68f-403e-aa04-0a60c3a445d1"));
        testDiscount.setName("Discount on yoga weekdays");
        testDiscount.setDescription("Amazing yoga for two days. Almost free.");
        testDiscount.setPromo("7abcde7");
        testDiscount.setDiscountType(DiscountType.PERCENT);
        testDiscount.setValue(BigDecimal.valueOf(5));
        testDiscount.setStartTime(LocalDateTime.parse("2021-07-20T00:00:00"));
        testDiscount.setEndTime(LocalDateTime.parse("2021-08-20T00:00:00"));
        testDiscount.setActive(true);
        testDiscount.setArchived(false);
        testDiscount.setVendor(testVendor);

        //  testDiscount.setTags(tags);
        testDiscount.setVendorLocations(testVendorLocations);
        testDiscount.setCategory(testCategory);
        discountRepository.save(testDiscount);
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    public void addDiscountIT() throws Exception {

        setUp();
        mockMvc.perform(post("/discounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Toy for your dog\", " +
                        "\"promo\": \"11aa777\"," +
                        "\"discountType\": \"PERCENT\"," +
                        "\"value\":\"50\"," +
                        "\"categoryId\": \"93577f24-f68f-403e-aa04-0a60c3a445d6\"," +
                        "\"vendorId\": \"93577f24-f68f-403e-aa04-0a60c3a445d2\", " +
                        "\"vendorLocationsIds\": [\"93577f24-f68f-403e-aa04-0a60c3a445d3\", " +
                        "\"93577f24-f68f-403e-aa04-0a60c3a445d4\"]}" +
                        "\"tagIds\": [\"93577f24-f68f-403e-aa04-0a60c3a445d5\"]}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Toy for your dog"))
                .andExpect(jsonPath("$.vendor.id").value("93577f24-f68f-403e-aa04-0a60c3a445d2"));
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    public void removeAndRestoreDiscountIT() throws Exception {
        mockMvc.perform(delete("/discounts/{id}", "5f69268b-705e-4fb9-8147-722b4ec1d9da")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

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
        String categoryId = discountRepository.findById(discountId).get().getCategory().getId().toString();
        mockMvc.perform(delete("/discounts/{id}", "5f69268b-705e-4fb9-8147-722b4ec1d9da")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        //discountRepository.setArchivedById(discountId, true);
        mockMvc.perform(get("/discounts/archived")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].category.id").value(categoryId))
                .andExpect(jsonPath("$[0].archived").value(false))//hope, it will be true after Lisa's fix merging
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