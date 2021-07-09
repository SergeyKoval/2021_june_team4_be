package com.exadel.discount.mapper;

import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.VendorDTO;
import com.exadel.discount.entity.Vendor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VendorLocationMapper.class})
public interface VendorMapper {

    Vendor parseDTO(VendorDTO vendorDTO);

    Vendor parseDTO(CreateVendorDTO vendorDTO);

    VendorDTO getDTO(Vendor vendor);

    List<VendorDTO> getListDTO(List<Vendor> vendors);
}
