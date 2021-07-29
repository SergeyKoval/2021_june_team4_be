package com.exadel.discount.controller;

import com.exadel.discount.model.dto.TagDTO;
import com.exadel.discount.model.dto.validation.Create;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    @ApiOperation("Get list of all tags")
    @UserAccess
    public List<TagDTO> getAllTags() {
        return tagService.getAllTags();
    }

    @PostMapping
    @ApiOperation("Save new tag")
    @AdminAccess
    public TagDTO saveTag(@Validated(Create.class) @RequestBody @NotNull TagDTO tag) {
        return tagService.saveTag(tag);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get tag by ID")
    @UserAccess
    public TagDTO getTagById(@PathVariable UUID id) {
        return tagService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete tag by ID")
    @AdminAccess
    public void deleteTag(@PathVariable UUID id) {
        tagService.deleteTagById(id);
    }

}