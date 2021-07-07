package com.exadel.discount.mapper;

import com.exadel.discount.dto.category.CategoryDTO;
import com.exadel.discount.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {

    Category parseDTO(CategoryDTO categoryDTO);

    CategoryDTO getDTO(Category category);

    List<CategoryDTO> getListDTO(List<Category> categories);
}
