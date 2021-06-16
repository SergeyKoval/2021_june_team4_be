package com.exadel.discount.mapper;

import com.exadel.discount.dto.DiscountDTO;
import com.exadel.discount.entity.Discount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    Discount parseDTO(DiscountDTO discountDTO);

    DiscountDTO getDTO(Discount discount);

    List<DiscountDTO> getListDTO(List<Discount> discounts);

}
