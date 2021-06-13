package com.exadel.discount.service;

import com.exadel.discount.entity.Category;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category get(UUID id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }
}
