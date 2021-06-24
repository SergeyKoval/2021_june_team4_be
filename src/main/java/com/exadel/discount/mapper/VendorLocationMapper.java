package com.exadel.discount.mapper;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.entity.VendorLocation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorLocationMapper {
    VendorLocation parseDTO(VendorLocationDTO vendorLocationDTO);

    VendorLocationDTO getDTO(VendorLocation vendorLocation);

    List<VendorLocationDTO> getListDTO(List<VendorLocation> vendorLocations);
}
