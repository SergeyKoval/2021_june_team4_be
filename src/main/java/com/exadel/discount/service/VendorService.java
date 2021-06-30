package com.exadel.discount.service;

import com.exadel.discount.dto.VendorDTO;

import java.util.List;
import java.util.UUID;

public interface VendorService {

    VendorDTO save(VendorDTO vendor);

    VendorDTO getById(UUID id);

    List<VendorDTO> getAll();

    void deleteById(UUID id);
}