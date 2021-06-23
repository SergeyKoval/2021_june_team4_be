package com.exadel.discount.service.interfaces;

import com.exadel.discount.dto.VendorDTO;

import java.util.List;
import java.util.UUID;

public interface VendorService {

    VendorDTO save(VendorDTO vendor);
    VendorDTO get(UUID id);
    List<VendorDTO> getAll();
    void delete(UUID id);
    long count();

}
