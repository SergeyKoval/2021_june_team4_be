package com.exadel.discount.controller;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.interfaces.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ApiOperation("Get all categories or category by id")
    public List<CategoryDTO> getAll(@RequestParam(name = "id", required = false) UUID id) {
        if (id == null) {
            return categoryService.getAll();
        } else {
            List<CategoryDTO> categories = new ArrayList<>();
            categories.add(categoryService.getById(id));
            return categories;
        }

    }

    /*@GetMapping("/{categoryId}")
    @ApiOperation("Get information about category by Id")
    public CategoryDTO getById(@PathVariable(name = "categoryId") @NotNull UUID id) {
        return categoryService.getById(id);
    }*/

    @PostMapping
    @ApiOperation("Add new category")
    public CategoryDTO addCategory(@RequestBody @Validated(Create.class) CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping()
    @ApiOperation("Delete category by Id")
    public void deleteCategory(@RequestParam(name = "categoryId", required = true) @NotNull UUID id) {
        categoryService.deleteById(id);
    }

}
