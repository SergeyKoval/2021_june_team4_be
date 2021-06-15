package com.exadel.discount.service;

import com.exadel.discount.dto.tag.CreateTagDTO;
import com.exadel.discount.dto.tag.TagDTO;

import java.util.List;
import java.util.UUID;

public interface TagService {
    List<TagDTO> getAllTags();

    TagDTO getById(UUID id);

    TagDTO saveTag(CreateTagDTO createTagDTO);

    void deleteTagById(UUID id);
}
