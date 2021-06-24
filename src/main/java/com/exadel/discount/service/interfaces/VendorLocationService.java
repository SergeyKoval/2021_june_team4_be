package com.exadel.discount.service.interfaces;

import com.exadel.discount.dto.VendorLocationDTO;

import java.util.List;
import java.util.UUID;

public interface VendorLocationService {

    VendorLocationDTO save(VendorLocationDTO vendorLocationDTO, UUID vendorId);
    VendorLocationDTO getById(UUID id);
    List<VendorLocationDTO> getAll();
    void deleteById(UUID vendorId, UUID locationId);
}
