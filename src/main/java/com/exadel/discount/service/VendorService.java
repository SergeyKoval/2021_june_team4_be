package com.exadel.discount.service;

import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.VendorDTO;

import java.util.List;
import java.util.UUID;

public interface VendorService {

    CreateVendorDTO save(CreateVendorDTO vendor);

    VendorDTO getById(UUID id);

    List<VendorDTO> getAll();

    void deleteById(UUID id);
}