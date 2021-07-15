package com.exadel.discount.service.impl;

import com.exadel.discount.model.dto.location.CreateLocationDTO;
import com.exadel.discount.model.dto.location.LocationDTO;
import com.exadel.discount.model.dto.location.UpdateLocationDTO;
import com.exadel.discount.model.entity.City;
import com.exadel.discount.model.entity.Vendor;
import com.exadel.discount.model.entity.VendorLocation;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.mapper.VendorLocationMapper;
import com.exadel.discount.repository.CityRepository;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.VendorLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Vendor vendor = findVendor(vendorLocation.getVendorId());
        City city = findCity(vendorLocation.getCityId());
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
        VendorLocation vendorLocation = findVendorLocation(id);
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

    @Override
    @Transactional
    public LocationDTO updateLocationById(UpdateLocationDTO updateLocationDTO, UUID id) {
        log.debug(String.format("Update VendorLocation with ID %s", id));
        VendorLocation vendorLocation = findVendorLocation(id);
        if (updateLocationDTO.getCityId() != null) {
            City city = findCity(updateLocationDTO.getCityId());
            vendorLocation.setCity(city);
        }
        if (updateLocationDTO.getVendorId() != null) {
            Vendor vendor = findVendor(updateLocationDTO.getVendorId());
            vendorLocation.setVendor(vendor);
        }
        vendorLocation = vendorLocationMapper.update(updateLocationDTO, vendorLocation);
        LocationDTO locationDTO = vendorLocationMapper.getDTO(vendorLocationRepository.save(vendorLocation));
        log.debug(String.format("Successfully update VendorLocation with ID %s", id));
        return locationDTO;
    }

    VendorLocation findVendorLocation(UUID id) {
        return vendorLocationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("VendorLocation %s not found", id)));
    }

    private City findCity(UUID id) {
        return cityRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("City with id %s not found", id)));
    }

    private Vendor findVendor(UUID id) {
        return vendorRepository
                .findByIdAndArchived(id, false)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with id %s not found", id)));
    }
}
