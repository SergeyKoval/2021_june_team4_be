package com.exadel.discount.service;

import com.exadel.discount.controller.AbstractIT;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.CategoryDTO;
import com.exadel.discount.model.dto.mapper.CategoryMapper;
import com.exadel.discount.model.entity.Category;
import com.exadel.discount.model.entity.Discount;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServiceTest extends AbstractIT {

    private static final UUID ID = UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6");

    @Autowired
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper mapper;

    private CategoryDTO input;

    @BeforeEach
    public void setUp() {
        input = CategoryDTO.builder()
                .name("test")
                .build();
    }

    @Test
    public void testSave() {
        var result = categoryService.save(input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetAll(){
        List<Category> expected = List.of(new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(expected);
        CategoryServiceImpl implS = Mockito.mock(CategoryServiceImpl.class);
        List<CategoryDTO> actual = implS.getAll();

        Assertions.assertEquals(actual, mapper.getListDTO(expected));
    }

    @Test
    public void testExceptionFindById(){
        Exception exception = assertThrows(NotFoundException.class,
                ()-> categoryService.getById(ID));
        Assertions.assertEquals("Vendor "+ID+" not found",exception.getMessage());

    }

    @Test
    public void testDeleteById(){
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(new Category()));
        doNothing().when(categoryRepository).delete(any(Category.class));
        categoryRepository.deleteById(UUID.randomUUID());
    }
    @Test
    public void testExceptionDeleteById(){
        Exception exception = assertThrows(NotFoundException.class,
                () -> categoryService.deleteById(ID));
        Assertions.assertEquals("Category with ID "+ID+" not found",exception.getMessage());
    }

    @Test
    public void testExceptionDeleteByIdWithDeletionRestrictedException(){
        Category category = new Category();
        category.setId(ID);
        List<Discount>discounts = new ArrayList<>();
        category.setDiscounts(discounts);
        Exception exception = assertThrows(NotFoundException.class,
                () -> categoryService.deleteById(ID));
        Assertions.assertEquals("Category with ID "+ID+" can't be deleted as it has discounts",exception.getMessage());
    }
}
