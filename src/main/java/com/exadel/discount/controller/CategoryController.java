package com.exadel.discount.controller;

import com.exadel.discount.model.dto.CategoryDTO;
import com.exadel.discount.model.dto.validation.Create;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @UserAccess
    public List<CategoryDTO> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    @ApiOperation("Get information about category by Id")
    @UserAccess
    public CategoryDTO getById(
            @PathVariable(name = "categoryId") @NotNull UUID id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ApiOperation("Add new category")
    @AdminAccess
    public CategoryDTO addCategory(@RequestBody @Validated(Create.class) CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping("/{id}")
    @AdminAccess
    @ApiOperation("Update category")
    public CategoryDTO updateCategory(@PathVariable @NotNull UUID id,
                                      @RequestBody @Validated(Create.class) CategoryDTO categoryDTO) {
        return categoryService.updateCategoryById(categoryDTO, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete category by Id")
    @AdminAccess
    public void deleteCategory(@PathVariable(name = "id") @NotNull UUID id) {
        categoryService.deleteById(id);
    }
}
