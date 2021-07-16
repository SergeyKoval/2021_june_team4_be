package com.exadel.discount.controller;


import com.exadel.discount.model.dto.TagDTO;
import com.exadel.discount.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class TagControllerTests {

    private static final UUID ID = UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6");
    private static final TagDTO tag;
    static {
        tag = new TagDTO();
        tag.setName("Test");
    }
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;


    @Test
    public void getAllTagsTest()throws Exception {
       // List<TagDTO> expected = tagService.getAllTags();
        mockMvc.perform(MockMvcRequestBuilders.get("/tags"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void saveTagTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\"" + "}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void saveTagExceptionTest() throws Exception{
        when(tagService.getById(UUID.randomUUID())).thenReturn(tag);

        mockMvc.perform(post("/tags")
                .param("name","Test")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    public void getTagByIdTest() throws Exception {
        when(tagService.getById(ID)).thenReturn(tag);
        mockMvc.perform(get("/tags")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteTagTest() throws Exception{
        mockMvc.perform(delete("/tags")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }
}
