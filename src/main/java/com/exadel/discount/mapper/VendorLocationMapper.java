package com.exadel.discount.mapper;

import com.exadel.discount.dto.location.CreateLocationDTO;
import com.exadel.discount.dto.location.LocationDTO;
import com.exadel.discount.entity.VendorLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface VendorLocationMapper {

    @Mapping(source = "latitude", target = "position.latitude")
    @Mapping(source = "longitude", target = "position.longitude")
    VendorLocation parseDTO(LocationDTO vendorLocationDTO);

    @Mapping(source = "latitude", target = "position.latitude")
    @Mapping(source = "longitude", target = "position.longitude")
    VendorLocation parseDTO(CreateLocationDTO createLocationDTO);

    @Mapping(source = "position.latitude", target = "latitude")
    @Mapping(source = "position.longitude", target = "longitude")
    LocationDTO getDTO(VendorLocation vendorLocation);

    List<LocationDTO> getListDTO(List<VendorLocation> vendorLocations);
}
