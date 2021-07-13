package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.TagDTO;
import com.exadel.discount.model.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toTag(TagDTO tagDTO);

    TagDTO toTagDto(Tag tag);

    List<TagDTO> toTagDtoList(List<Tag> tags);
}
