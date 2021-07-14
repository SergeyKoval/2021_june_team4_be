package com.exadel.discount.service;

import com.exadel.discount.model.dto.vendor.BaseVendorDTO;
import com.exadel.discount.model.dto.vendor.CreateVendorDTO;
import com.exadel.discount.model.dto.vendor.VendorDTO;

import java.util.List;
import java.util.UUID;

public interface VendorService {

    VendorDTO save(CreateVendorDTO vendor);

    VendorDTO getById(UUID id);

    List<BaseVendorDTO> getAll();

    void deleteById(UUID id);

    List<BaseVendorDTO> getAllArchived();

    VendorDTO restoreById(UUID id);

    VendorDTO updateVendorById(BaseVendorDTO vendorDTO, UUID id);
}
