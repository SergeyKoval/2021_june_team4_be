package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.discount.DiscountImageDTO;
import com.exadel.discount.model.entity.DiscountImage;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    DiscountImage toDiscountImage(DiscountImageDTO discountImageDTO);

    DiscountImageDTO toDiscountImageDTO(DiscountImage discountImage);

    Set<DiscountImageDTO> toDiscountImageDTOSet(Set<DiscountImage> discountImages);

    Set<DiscountImage> toDiscountImageSet(Set<DiscountImageDTO> discountImages);
}
