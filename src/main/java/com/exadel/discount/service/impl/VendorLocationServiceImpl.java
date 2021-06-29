package com.exadel.discount.service.impl;

import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.service.VendorLocationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorLocationServiceImpl implements VendorLocationService {

    @Override
    public VendorLocation create(VendorLocation vendorLocation, UUID vendorId) {
        Vendor vendor = new Vendor();
        vendor.setId(vendorId);
        vendorLocation.setVendor(vendor);
        return vendorLocation;
    }
}
