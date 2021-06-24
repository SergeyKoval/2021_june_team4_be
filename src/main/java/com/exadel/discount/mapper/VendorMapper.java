package com.exadel.discount.mapper;

import com.exadel.discount.dto.VendorDTO;
import com.exadel.discount.entity.Vendor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    Vendor parseDTO(VendorDTO vendorDTO);

    VendorDTO getDTO(Vendor vendor);

    List<VendorDTO> getListDTO(List<Vendor> vendors);
}
