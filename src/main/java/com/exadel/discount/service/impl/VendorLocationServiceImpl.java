package com.exadel.discount.service.impl;

import com.exadel.discount.dto.location.CreateLocationDTO;
import com.exadel.discount.dto.location.LocationDTO;
import com.exadel.discount.entity.City;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.exception.custom_exception.NotFoundException;
import com.exadel.discount.mapper.VendorLocationMapper;
import com.exadel.discount.repository.CityRepository;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.VendorLocationService;
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
    private final VendorRepository vendorRepository;
    private final CityRepository cityRepository;
    private final VendorLocationMapper vendorLocationMapper;

    @Override
    public LocationDTO save(CreateLocationDTO vendorLocation) {
        log.debug("Saving new VendorLocation");
        UUID vendorId = vendorLocation.getVendorId();
        Vendor vendor = vendorRepository
                .findByIdAndArchived(vendorId, false)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with id %s not found", vendorId)));
        UUID cityId = vendorLocation.getCityId();
        City city = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new NotFoundException(String.format("City with id %s not found", cityId)));
        VendorLocation location = vendorLocationMapper.parseDTO(vendorLocation);
        location.setVendor(vendor);
        location.setCity(city);
        VendorLocation savedVendorLocation = vendorLocationRepository.save(location);
        log.debug("Successfully saved new VendorLocation");
        return vendorLocationMapper.getDTO(savedVendorLocation);
    }

    @Override
    public LocationDTO getById(UUID id) {
        log.debug(String.format("Finding VendorLocation with ID %s", id));
        VendorLocation vendorLocation = vendorLocationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("VendorLocation %s not found", id)));
        log.debug(String.format("Successfully found VendorLocation with ID %s", id));
        return vendorLocationMapper.getDTO(vendorLocation);
    }

    @Override
    public List<LocationDTO> getLocationsByVendorId(UUID id) {
        log.debug("Getting list of all Vendors");
        if (!vendorRepository.existsByIdAndArchived(id, false)) {
            throw new NotFoundException(String.format("Vendor with id %s not found", id));
        }
        List<VendorLocation> vendorLocationList = vendorLocationRepository.findByVendorId(id);
        log.debug("Successfully got list of all Vendors");
        return vendorLocationMapper.getListDTO(vendorLocationList);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug(String.format("Deleting VendorLocation with ID %s", id));
        if (!vendorLocationRepository.existsById(id)) {
            throw new NotFoundException(String.format("VendorLocation with ID %s not found", id));
        }
        vendorLocationRepository.deleteById(id);
        log.debug(String.format("Successfully deleted VendorLocation with ID %s", id));
    }
}
