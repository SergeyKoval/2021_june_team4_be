package com.exadel.discount.mapper;

import com.exadel.discount.dto.location.CreateLocationDTO;
import com.exadel.discount.dto.location.LocationDTO;
import com.exadel.discount.entity.VendorLocation;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface VendorLocationMapper {

    VendorLocation parseDTO(LocationDTO vendorLocationDTO);

    VendorLocation parseDTO(CreateLocationDTO createLocationDTO);

    LocationDTO getDTO(VendorLocation vendorLocation);

    List<LocationDTO> getListDTO(List<VendorLocation> vendorLocations);
}
