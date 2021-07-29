package com.exadel.discount.service;

import com.exadel.discount.model.dto.TagDTO;

import java.util.List;
import java.util.UUID;

public interface TagService {
    List<TagDTO> getAllTags();

    TagDTO getById(UUID id);

    TagDTO saveTag(TagDTO tagDTO);

    void deleteTagById(UUID id);
}
