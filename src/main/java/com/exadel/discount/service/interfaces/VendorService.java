package com.exadel.discount.service.interfaces;

import com.exadel.discount.entity.Vendor;

import java.util.List;
import java.util.UUID;

public interface VendorService {

    Vendor create(Vendor vendor);
    Vendor get(UUID id);
    List<Vendor> getAll();
    long count();

}
