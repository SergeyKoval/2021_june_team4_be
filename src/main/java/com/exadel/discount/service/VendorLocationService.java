package com.exadel.discount.service;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.entity.VendorLocation;

import java.util.List;
import java.util.UUID;

public interface VendorLocationService {

    VendorLocationDTO save(VendorLocation vendorLocation, UUID vendorId);

    VendorLocationDTO getById(UUID id);

    List<VendorLocationDTO> getAll(UUID vendorId);

    void deleteById(UUID locationId);
}
