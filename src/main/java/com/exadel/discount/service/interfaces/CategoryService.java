package com.exadel.discount.service.interfaces;

import com.exadel.discount.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category create(Category category);
    Category get(UUID id);
    List<Category> getAll();
    long count();


}
