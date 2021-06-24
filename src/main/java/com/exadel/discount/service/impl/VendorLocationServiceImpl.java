package com.exadel.discount.service.impl;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.VendorLocationMapper;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.interfaces.VendorLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class VendorLocationServiceImpl implements VendorLocationService {

    private final VendorLocationRepository vendorLocationRepository;
    private final VendorLocationMapper vendorLocationMapper;
    private final VendorRepository vendorRepository;

    @Override
    public VendorLocationDTO save(VendorLocationDTO vendorLocationDTO, UUID vendorId)  {
        log.debug("Saving new VendorLocation");
        Vendor vendor = vendorRepository.
                findById(vendorId).
                orElseThrow(() -> new NotFoundException(String.format("Vendor %s not found", vendorId)));
        VendorLocation vendorLocation = vendorLocationMapper.parseDTO(vendorLocationDTO);
        vendorLocation.setVendor(vendor);
        VendorLocation vendorLocation1 = vendorLocationRepository.save(vendorLocation);
        log.debug("Successfully saved new VendorLocation");
        return vendorLocationMapper.getDTO(vendorLocation1);
    }

    @Override
    public VendorLocationDTO getById(UUID id) {
        log.debug(String.format("Finding VendorLocation with ID %s", id));
        VendorLocation vendorLocation = vendorLocationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("VendorLocation with id %s not found",id)));
        log.debug(String.format("Successfully found VendorLocation with ID %s", id));
        return vendorLocationMapper.getDTO(vendorLocation);
    }

    @Override
    public List<VendorLocationDTO> getAll() {
        log.debug("Getting list of all VendorLocations");
        List<VendorLocation> vendorLocations = vendorLocationRepository.findAll();
        log.debug("Successfully got list of all VendorLocations");
        return vendorLocationMapper.getListDTO(vendorLocations);
    }

    @Override
    public void deleteById(UUID vendorId, UUID locationId)  {
        log.debug(String.format("Deleting VendorLocations with ID %s", locationId));
        Vendor vendor = vendorRepository
                .findById(vendorId)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor %s not found", vendorId)));
        if (vendorLocationRepository
                .findById(locationId)
                .orElseThrow(() -> new NotFoundException(String.format("VendorLocation %s not found", locationId)))
                .getVendor()
                .getId()
                .equals(vendorId)) {
            vendorLocationRepository.deleteById(locationId);
            log.debug(String.format("Successfully deleted VendorLocations with ID %s", locationId));
        } else {
            log.debug(String.format("Error deleted VendorLocations with ID %s", locationId));
        }
    }

}
