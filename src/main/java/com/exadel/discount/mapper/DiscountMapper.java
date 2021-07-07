package com.exadel.discount.mapper;

import com.exadel.discount.dto.discount.CreateDiscountDTO;
import com.exadel.discount.dto.discount.DiscountDTO;
import com.exadel.discount.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VendorLocationMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DiscountMapper {

    Discount parseDTO(DiscountDTO discountDTO);

    DiscountDTO getDTO(Discount discount);

    List<DiscountDTO> getListDTO(List<Discount> discounts);

    Discount parseDTO(CreateDiscountDTO discounts);
}
