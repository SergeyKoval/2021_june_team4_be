package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.discount.CreateDiscountDTO;
import com.exadel.discount.model.dto.discount.DiscountDTO;
import com.exadel.discount.model.entity.Discount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VendorLocationMapper.class, ImageMapper.class})
public interface DiscountMapper {

    Discount parseDTO(DiscountDTO discountDTO);

    DiscountDTO getDTO(Discount discount);

    List<DiscountDTO> getListDTO(List<Discount> discounts);

    Discount parseDTO(CreateDiscountDTO discounts);
}
