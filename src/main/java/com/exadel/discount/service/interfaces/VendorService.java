package com.exadel.discount.service.interfaces;

import com.exadel.discount.entity.Vendor;

import java.util.List;

public interface VendorService {

    Vendor create(Vendor vendor);
    Vendor get(Long id);
    List<Vendor> getAll();
    long count();

}
