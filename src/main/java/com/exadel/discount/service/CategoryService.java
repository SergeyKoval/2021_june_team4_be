package com.exadel.discount.service;

import com.exadel.discount.dto.category.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO getById(UUID id);

    void deleteById(UUID id);

    List<CategoryDTO> getAll();

    CategoryDTO updateCategoryById(CategoryDTO categoryDTO, UUID id);
}