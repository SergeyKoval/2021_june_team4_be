package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.vendor.BaseVendorDTO;
import com.exadel.discount.model.dto.vendor.CreateVendorDTO;
import com.exadel.discount.model.dto.vendor.VendorDTO;
import com.exadel.discount.model.entity.Vendor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VendorLocationMapper.class, DiscountMapper.class})
public interface VendorMapper {

    Vendor parseDTO(VendorDTO vendorDTO);

    Vendor parseDTO(CreateVendorDTO vendorDTO);

    VendorDTO getDTO(Vendor vendor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Vendor update(BaseVendorDTO vendorDTO, @MappingTarget Vendor vendor);

    List<VendorDTO> getListDTO(List<Vendor> vendors);

    BaseVendorDTO getBaseDTO(Vendor vendor);

    List<BaseVendorDTO> getListBaseDTO(List<Vendor> vendors);
}