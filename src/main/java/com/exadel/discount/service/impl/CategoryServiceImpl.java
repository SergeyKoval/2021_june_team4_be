package com.exadel.discount.service.impl;

import com.exadel.discount.model.dto.CategoryDTO;
import com.exadel.discount.model.entity.Category;
import com.exadel.discount.exception.DeletionRestrictedException;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.mapper.CategoryMapper;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        CategoryDTO category = categoryMapper.getDTO(categoryRepository.save(categoryMapper.parseDTO(categoryDTO)));
        log.debug("Successfully saved new Category");
        return category;
    }

    @Override
    @Transactional
    public CategoryDTO updateCategoryById(CategoryDTO categoryDTO, UUID id) {
        log.debug(String.format("Update Category with ID %s", id));
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(String.format("Category with ID %s not found", id));
        }
        Category category = new Category();
        category = categoryMapper.update(categoryDTO, category);
        category.setId(id);
        return categoryMapper.getDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO getById(UUID id) {
        log.debug(String.format("Finding Category with ID %s", id));
        Category category = categoryRepository.
                findById(id).
                orElseThrow(() -> new NotFoundException(String.format("Vendor %s not found", id)));
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
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category with ID %s not found", id)));
        if (!category.getDiscounts().isEmpty()) {
            throw new DeletionRestrictedException(String.
                    format("Category with ID %s can't be deleted as it has discounts", id));
        }
        categoryRepository.deleteById(id);
        log.debug(String.format("Successfully deleted Category with ID %s", id));
    }
}