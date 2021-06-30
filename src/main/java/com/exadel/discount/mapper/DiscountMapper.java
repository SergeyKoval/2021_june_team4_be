package com.exadel.discount.mapper;

import com.exadel.discount.dto.discount.CreateDiscountDTO;
import com.exadel.discount.dto.discount.DiscountDTO;
import com.exadel.discount.dto.vendor.BaseVendorDTO;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    Discount parseDTO(DiscountDTO discountDTO);

    @Mapping(target = "vendor", expression = "java(getVendor(discount))")
    DiscountDTO getDTO(Discount discount);

    List<DiscountDTO> getListDTO(List<Discount> discounts);

    Discount parseDTO(CreateDiscountDTO discounts);

    default BaseVendorDTO getVendor(Discount discount) {
        Set<VendorLocation> vendorLocations = discount.getVendorLocations();
        if (!discount.getVendorLocations().isEmpty()) {
            List<VendorLocation> vendorLocationsList = new ArrayList<>(vendorLocations);
            return getDTO(vendorLocationsList.get(0).getVendor());
        }
        return null;
    }

    BaseVendorDTO getDTO(Vendor vendor);
}
