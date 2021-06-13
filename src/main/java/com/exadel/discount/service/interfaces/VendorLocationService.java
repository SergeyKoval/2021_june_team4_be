package com.exadel.discount.service.interfaces;

import com.exadel.discount.entity.VendorLocation;

import java.util.List;
import java.util.UUID;

public interface VendorLocationService {

    VendorLocation create(VendorLocation locationVendor);
    VendorLocation get(UUID id);
    List<VendorLocation> getAll();
    long count();

}
