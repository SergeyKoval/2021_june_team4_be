package com.exadel.discount.service;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.mapper.CategoryMapper;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        return categoryMapper.getDTO(categoryRepository.save(categoryMapper.parseDTO(categoryDTO)));
    }

    @Override
    public CategoryDTO get(UUID id) {
        return categoryMapper.getDTO(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryMapper.getListDTO(categoryRepository.findAll());
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }
}
