package com.exadel.discount.service;

import com.exadel.discount.dto.category.CategoryDTO;
import com.exadel.discount.dto.category.UpdateCategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO getById(UUID id);

    void deleteById(UUID id);

    List<CategoryDTO> getAll();

    UpdateCategoryDTO updateCategory(UpdateCategoryDTO updateCategoryDTO);
}