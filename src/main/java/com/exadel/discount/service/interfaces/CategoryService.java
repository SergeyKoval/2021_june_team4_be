package com.exadel.discount.service.interfaces;

import com.exadel.discount.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO get(UUID id);
    List<CategoryDTO> getAll();
    long count();


}
