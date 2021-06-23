package com.exadel.discount.service;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.mapper.VendorLocationMapper;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.interfaces.VendorLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VendorLocationServiceImpl implements VendorLocationService {

    private final VendorLocationRepository vendorLocationRepository;
    private final VendorLocationMapper vendorLocationMapper;
    private final VendorRepository vendorRepository;

    @Override
    public VendorLocationDTO save(VendorLocationDTO vendorLocationDTO, UUID vendorId) {
        VendorLocation vendorLocation = vendorLocationMapper.parseDTO(vendorLocationDTO);
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();
        vendorLocation.setVendor(vendor);
        VendorLocation vendorLocation1 = vendorLocationRepository.save(vendorLocation);
        return vendorLocationMapper.getDTO(vendorLocation1);
    }

    @Override
    public VendorLocationDTO get(UUID id) {
        return vendorLocationMapper.getDTO(vendorLocationRepository.findById(id).orElse(null));
    }

    @Override
    public List<VendorLocationDTO> getAll() {
        return vendorLocationMapper.getListDTO(vendorLocationRepository.findAll());
    }

    @Override
    public void delete(UUID vendorId, UUID locationId) throws Exception {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new Exception(String.format("Vendor %s not found", vendorId)));
        if (vendorLocationRepository
                .findById(locationId)
                .orElseThrow(() -> new Exception(String.format("Location %s not found", locationId)))
                .getVendor()
                .getId()
                .equals(vendorId)) {
            vendorLocationRepository.deleteById(locationId);
        }
    }

    @Override
    public long count() {
        return vendorLocationRepository.count();
    }
}
