package com.exadel.discount.mapper;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category parseDTO(CategoryDTO categoryDTO);

    CategoryDTO getDTO(Category category);

    List<CategoryDTO> getListDTO(List<Category> categories);
}
