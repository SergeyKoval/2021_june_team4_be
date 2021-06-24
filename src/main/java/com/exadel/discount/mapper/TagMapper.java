package com.exadel.discount.mapper;

import com.exadel.discount.dto.TagDTO;
import com.exadel.discount.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toTag(TagDTO tagDTO);

    TagDTO toTagDto(Tag tag);

    List<TagDTO> toTagDtoList(List<Tag> tags);
}
