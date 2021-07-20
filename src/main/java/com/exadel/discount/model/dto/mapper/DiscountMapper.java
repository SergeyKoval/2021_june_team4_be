package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.discount.BaseDiscountDTO;
import com.exadel.discount.model.dto.discount.CreateDiscountDTO;
import com.exadel.discount.model.dto.discount.DiscountDTO;
import com.exadel.discount.model.dto.discount.UpdateDiscountDTO;
import com.exadel.discount.model.entity.Discount;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VendorLocationMapper.class})
public interface DiscountMapper {

    Discount parseDTO(DiscountDTO discountDTO);

    DiscountDTO getDTO(Discount discount);

    List<DiscountDTO> getListDTO(List<Discount> discounts);

    Discount parseDTO(CreateDiscountDTO discounts);

    BaseDiscountDTO getBaseDTO(Discount discount);

    List<BaseDiscountDTO> getListBaseDTO(List<Discount> discounts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Discount update(UpdateDiscountDTO updateDiscountDTO, @MappingTarget Discount discount);
}
