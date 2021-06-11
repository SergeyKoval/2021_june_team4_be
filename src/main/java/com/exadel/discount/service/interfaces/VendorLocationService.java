package com.exadel.discount.service.interfaces;

import com.exadel.discount.entity.VendorLocation;

import java.util.List;

public interface VendorLocationService {

    VendorLocation create(VendorLocation locationVendor);
    VendorLocation get(Long id);
    List<VendorLocation> getAll();
    long count();

}
