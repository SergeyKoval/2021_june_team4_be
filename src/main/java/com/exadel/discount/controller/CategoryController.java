package com.exadel.discount.controller;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Category;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CategoryMapper;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.service.CategoryService;
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

    private final CategoryService categoryService;

    @GetMapping
    @ApiOperation("Get all categories")
    public List<CategoryDTO> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    @ApiOperation("Get information about category by Id")
    public CategoryDTO getById(
            @PathVariable(name = "categoryId") @NotNull UUID id) {
            return categoryService.getById(id);
    }

    @PostMapping
    @ApiOperation("Add new category")
    public CategoryDTO addCategory(@RequestBody @Validated(Create.class) CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping
    @ApiOperation("Delete category by Id")
    public void deleteCategory(@RequestParam(name = "id", required = true) @NotNull UUID id) {
        categoryService.deleteById(id);
    }

}