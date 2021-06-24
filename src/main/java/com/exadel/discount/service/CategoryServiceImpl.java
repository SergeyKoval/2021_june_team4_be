package com.exadel.discount.service;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.entity.Category;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CategoryMapper;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.debug("Saving new Category");
        Category category = categoryRepository.save(categoryMapper.parseDTO(categoryDTO));
        log.debug("Successfully saved new Category");
        return categoryMapper.getDTO(category);
    }

    @Override
    public CategoryDTO getById(UUID id) {
        log.debug(String.format("Finding Category with ID %s", id));
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", id)));
        log.debug(String.format("Successfully found Category with ID %s", id));
        return categoryMapper.getDTO(category);
    }

    @Override
    public List<CategoryDTO> getAll() {
        log.debug("Getting list of all Categories");
        List<Category> categories = categoryRepository.findAll();
        log.debug("Successfully got list of all Categories");
        return categoryMapper.getListDTO(categories);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug(String.format("Deleting Category with ID %s", id));
        categoryRepository.deleteById(id);
        log.debug(String.format("Successfully deleted Category with ID %s", id));
    }

}
