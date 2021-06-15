package com.exadel.discount.service.impl;

import com.exadel.discount.dto.TagDTO;
import com.exadel.discount.entity.Tag;
import com.exadel.discount.mapper.TagMapper;
import com.exadel.discount.repository.TagRepository;
import com.exadel.discount.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagDTO> getAllTags() {
        List<Tag> allTags = tagRepository.findAll();
        return tagMapper.toTagDtoList(allTags);
    }

    @Override
    public TagDTO getById(UUID id) {
        return tagRepository
                .findById(id)
                .map(tag -> tagMapper.toTagDto(tag))
                .get();
    }

    @Override
    public TagDTO saveTag(TagDTO tagDTO) {
        Tag newTag = tagRepository.save(tagMapper.toTag(tagDTO));
        return tagMapper.toTagDto(newTag);
    }

    @Override
    public void deleteTagById(UUID id) {
        tagRepository.deleteById(id);
    }

}
