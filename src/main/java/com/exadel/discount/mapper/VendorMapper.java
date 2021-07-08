package com.exadel.discount.mapper;

import com.exadel.discount.dto.vendor.BaseVendorDTO;
import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.VendorDTO;
import com.exadel.discount.entity.Vendor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VendorLocationMapper.class})
public interface VendorMapper {

    Vendor parseDTO(VendorDTO vendorDTO);

    Vendor parseDTO(CreateVendorDTO vendorDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Vendor update(BaseVendorDTO vendorDTO, @MappingTarget Vendor vendor);

    VendorDTO getDTO(Vendor vendor);

    BaseVendorDTO getBaseDTO(Vendor vendor);

    List<VendorDTO> getListDTO(List<Vendor> vendors);
}
