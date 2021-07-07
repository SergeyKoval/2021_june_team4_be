package com.exadel.discount.mapper;

import com.exadel.discount.dto.vendor.BaseVendorDTO;
import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.VendorDTO;
import com.exadel.discount.entity.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VendorLocationMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface VendorMapper {

    Vendor parseDTO(VendorDTO vendorDTO);

    Vendor parseDTO(CreateVendorDTO vendorDTO);

    Vendor parseDTO(BaseVendorDTO vendorDTO);

    VendorDTO getDTO(Vendor vendor);

    BaseVendorDTO getBaseDTO(Vendor vendor);

    List<VendorDTO> getListDTO(List<Vendor> vendors);
}
