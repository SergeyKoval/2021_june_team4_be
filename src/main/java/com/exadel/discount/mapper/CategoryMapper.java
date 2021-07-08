package com.exadel.discount.mapper;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category parseDTO(CategoryDTO categoryDTO);

    CategoryDTO getDTO(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category update(CategoryDTO categoryDTO, @MappingTarget Category category);

    List<CategoryDTO> getListDTO(List<Category> categories);
}
