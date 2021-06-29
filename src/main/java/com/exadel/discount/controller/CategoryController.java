package com.exadel.discount.controller;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Category;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CategoryMapper;
import com.exadel.discount.repository.CategoryRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @GetMapping
    @ApiOperation("Get all categories")
    public List<CategoryDTO> getAll() {
        log.debug("Getting list of all Categories");
        List<Category> categories = categoryRepository.findAll();
        log.debug("Successfully got list of all Categories");
        return categoryMapper.getListDTO(categories);
    }

    @GetMapping("/{categoryId}")
    @ApiOperation("Get information about category by Id")
    public CategoryDTO getById(
            @PathVariable(name = "categoryId") @NotNull UUID id) {
        log.debug(String.format("Finding Category with ID %s", id));
        Category category = categoryRepository.
                findById(id).
                orElseThrow(() -> new NotFoundException(String.format("Vendor %s not found", id)));
        log.debug(String.format("Successfully found Category with ID %s", id));
        return categoryMapper.getDTO(category);
    }

    @PostMapping
    @ApiOperation("Add new category")
    public CategoryDTO addCategory(@RequestBody @Validated(Create.class) CategoryDTO categoryDTO) {
        log.debug("Saving new Category");
        CategoryDTO category = categoryMapper.getDTO(categoryRepository.save(categoryMapper.parseDTO(categoryDTO)));
        log.debug("Successfully saved new Category");
        return category;
    }

    @DeleteMapping
    @ApiOperation("Delete category by Id")
    public void deleteCategory(@RequestParam(name = "id", required = true) @NotNull UUID id) {
        log.debug(String.format("Deleting Category with ID %s", id));
        categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Category with ID %s not found",id)));
        categoryRepository.deleteById(id);
        log.debug(String.format("Successfully deleted Category with ID %s", id));
    }

}
