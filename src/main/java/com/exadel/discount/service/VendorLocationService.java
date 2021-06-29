package com.exadel.discount.service;

import com.exadel.discount.entity.VendorLocation;

import java.util.UUID;

public interface VendorLocationService {

    VendorLocation create(VendorLocation vendorLocation, UUID vendorId);
}
