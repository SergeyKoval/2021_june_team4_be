package com.exadel.discount.mapper;

import com.exadel.discount.dto.tag.CreateTagDTO;
import com.exadel.discount.dto.tag.TagDTO;
import com.exadel.discount.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toTag(CreateTagDTO createTagDTO);

    TagDTO toTagDto(Tag tag);

    List<TagDTO> toTagDtoList(List<Tag> tags);
}
