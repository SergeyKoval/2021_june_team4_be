package com.exadel.discount.service;

import com.exadel.discount.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO updateCategoryById(CategoryDTO categoryDTO, UUID id);

    CategoryDTO getById(UUID id);

    void deleteById(UUID id);

    List<CategoryDTO> getAll();
}