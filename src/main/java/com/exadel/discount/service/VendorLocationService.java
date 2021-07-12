package com.exadel.discount.service;

import com.exadel.discount.model.dto.location.CreateLocationDTO;
import com.exadel.discount.model.dto.location.LocationDTO;

import java.util.List;
import java.util.UUID;

public interface VendorLocationService {

    LocationDTO save(CreateLocationDTO createLocationDTO);

    LocationDTO getById(UUID id);

    List<LocationDTO> getLocationsByVendorId(UUID vendorId);

    void deleteById(UUID locationId);
}
