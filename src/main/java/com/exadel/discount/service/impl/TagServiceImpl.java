package com.exadel.discount.service.impl;

import com.exadel.discount.dto.TagDTO;
import com.exadel.discount.entity.Tag;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.TagMapper;
import com.exadel.discount.repository.TagRepository;
import com.exadel.discount.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagDTO> getAllTags() {
        log.info("Getting list of all Tags");

        List<Tag> allTags = tagRepository.findAll();
        log.info("Successfully got list of all Tags");

        return tagMapper.toTagDtoList(allTags);
    }

    @Override
    public TagDTO getById(UUID id) {
        log.info("Finding Tag by ID");

        Tag tag = tagRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Tag with id %s not found", id)));
        log.info("Successfully found Tag by ID");

        return tagMapper.toTagDto(tag);
    }

    @Override
    public TagDTO saveTag(TagDTO tagDTO) {
        log.info("Saving new Tag");

        Tag newTag = tagRepository.save(tagMapper.toTag(tagDTO));
        log.info("Successfully saved new Tag");

        return tagMapper.toTagDto(newTag);
    }

    @Override
    public void deleteTagById(UUID id) {
        log.info("Deleting Tag");

        tagRepository.deleteById(id);
        log.info("Successfully deleted Tag");
    }

}
