package com.exadel.discount.mapper;

import com.exadel.discount.dto.category.CategoryDTO;
import com.exadel.discount.dto.category.UpdateCategoryDTO;
import com.exadel.discount.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category parseDTO(CategoryDTO categoryDTO);

    Category parseDTO(UpdateCategoryDTO categoryDTO);

    CategoryDTO getDTO(Category category);

    UpdateCategoryDTO getUpdateDTO(Category category);

    List<CategoryDTO> getListDTO(List<Category> categories);
}
