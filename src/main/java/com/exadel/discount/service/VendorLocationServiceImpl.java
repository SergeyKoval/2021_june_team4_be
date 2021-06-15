package com.exadel.discount.service;

import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.service.interfaces.VendorLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VendorLocationServiceImpl implements VendorLocationService {

    private final VendorLocationRepository vendorLocationRepository;

    @Override
    public VendorLocation create(VendorLocation vendorLocation) {
        return vendorLocationRepository.save(vendorLocation);
    }

    @Override
    public VendorLocation get(UUID id) {
        return vendorLocationRepository.getOne(id);
    }

    @Override
    public List<VendorLocation> getAll() {
        return vendorLocationRepository.findAll();
    }

    @Override
    public long count() {
        return vendorLocationRepository.count();
    }
}
