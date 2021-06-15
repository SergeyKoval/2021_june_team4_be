package com.exadel.discount.controller;

import com.exadel.discount.dto.TagDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    @ApiOperation("Get list of all tags")
    public List<TagDTO> getAllTags() {
        return tagService.getAllTags();
    }

    @PostMapping
    @ApiOperation("Save new tag")
    public TagDTO saveTag(@Validated({Create.class}) @RequestBody TagDTO tag) {
        return tagService.saveTag(tag);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get tag by ID")
    public TagDTO getTagById(@PathVariable(name = "id") UUID id) {
        return tagService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete tag by ID")
    public void deleteTag(@PathVariable(name = "id") UUID id) {
        tagService.deleteTagById(id);
    }

}