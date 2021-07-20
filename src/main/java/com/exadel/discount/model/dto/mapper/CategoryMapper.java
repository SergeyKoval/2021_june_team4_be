package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.CategoryDTO;
import com.exadel.discount.model.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category parseDTO(CategoryDTO categoryDTO);

    CategoryDTO getDTO(Category category);

    List<CategoryDTO> getListDTO(List<Category> categories);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category update(CategoryDTO categoryDTO, @MappingTarget Category category);
}
