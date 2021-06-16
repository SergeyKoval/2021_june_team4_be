package com.exadel.discount.service.interfaces;

import com.exadel.discount.dto.VendorLocationDTO;

import java.util.List;
import java.util.UUID;

public interface VendorLocationService {

    VendorLocationDTO create(VendorLocationDTO vendorLocationDTO);
    VendorLocationDTO get(UUID id);
    List<VendorLocationDTO> getAll();
    long count();

}
