package com.exadel.discount.controller;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.service.interfaces.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

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
    public CategoryDTO getById(@PathVariable(name = "categoryId") @NotNull UUID id) {
        return categoryService.get(id);
    }

    @PostMapping
    @ApiOperation("Add new category")
    public CategoryDTO addCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    @ApiOperation("Delete category by Id")
    public void deleteCategory(@PathVariable(name = "categoryId") @Valid @NotNull UUID id) {
        categoryService.delete(id);
    }

}
