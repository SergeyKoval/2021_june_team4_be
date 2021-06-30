package com.exadel.discount.service.impl;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.VendorLocationMapper;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.service.VendorLocationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorLocationServiceImpl implements VendorLocationService {

    private final VendorLocationRepository vendorLocationRepository;
    private final VendorLocationMapper vendorLocationMapper;

    @Override
    public VendorLocationDTO save(VendorLocation vendorLocation, UUID vendorId) {
        log.debug("Saving new VendorLocation");
        Vendor vendor = new Vendor();
        vendor.setId(vendorId);
        vendorLocation.setVendor(vendor);
        VendorLocation savedVendorLocation = vendorLocationRepository.save(vendorLocation);
        log.debug("Successfully saved new VendorLocation");
        return vendorLocationMapper.getDTO(savedVendorLocation);
    }

    @Override
    public VendorLocationDTO getById(UUID id) {
        log.debug(String.format("Finding VendorLocation with ID %s", id));
        VendorLocation vendorLocation = vendorLocationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("VendorLocation %s not found", id)));
        log.debug(String.format("Successfully found VendorLocation with ID %s", id));
        return vendorLocationMapper.getDTO(vendorLocation);
    }

    @Override
    public List<VendorLocationDTO> getAll(UUID id) {
        log.debug("Getting list of all Vendors");
        Vendor vendor = new Vendor();
        vendor.setId(id);
        List<VendorLocation> vendorLocationList = vendorLocationRepository.findByVendor(vendor);
        log.debug("Successfully got list of all Vendors");
        return vendorLocationMapper.getListDTO(vendorLocationList);
    }

    @Override
    public void deleteById(UUID id)  {
        log.debug(String.format("Deleting VendorLocation with ID %s", id));
        vendorLocationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("VendorLocation %s not found", id)));
        vendorLocationRepository.deleteById(id);
        log.debug(String.format("Successfully deleted VendorLocation with ID %s", id));
    }
}
