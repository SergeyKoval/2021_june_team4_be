package com.exadel.discount.controller;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.VendorLocationMapper;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.service.VendorLocationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class VendorLocationController {

    private final VendorLocationRepository vendorLocationRepository;
    private final VendorLocationMapper vendorLocationMapper;
    private final VendorLocationService vendorLocationService;

    @GetMapping("/{vendorId}")
    @ApiOperation("Get all VendorLocations vendor's")
    List<VendorLocationDTO> getAll(@PathVariable("vendorId") @NotNull UUID id) {
        log.debug("Getting list of all Vendors");
        Vendor vendor = new Vendor();
        vendor.setId(id);
        List<VendorLocation> vendorLocationList = vendorLocationRepository.findByVendor(vendor);
        log.debug("Successfully got list of all Vendors");
        return vendorLocationMapper.getListDTO(vendorLocationList);
    }

    @GetMapping
    VendorLocationDTO getById(@RequestParam(value = "id", required = true) @NotNull UUID id) {
        log.debug(String.format("Finding VendorLocation with ID %s", id));
        VendorLocation vendorLocation = vendorLocationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("VendorLocation %s not found", id)));
        log.debug(String.format("Successfully found VendorLocation with ID %s", id));
        return vendorLocationMapper.getDTO(vendorLocation);
    }


    @PostMapping
    @ApiOperation("Add new location of vendor")
    public VendorLocationDTO addVendorLocation(@RequestParam(name = "vendorId", required = true) @NotNull UUID vendorId,
                                               @RequestBody @Validated VendorLocation vendorLocation) {
        log.debug("Saving new VendorLocation");
        VendorLocation savedVendorLocation = vendorLocationRepository.save(vendorLocationService.create(vendorLocation, vendorId));
        log.debug("Successfully saved new VendorLocation");
        return vendorLocationMapper.getDTO(savedVendorLocation);
    }

    @DeleteMapping
    @ApiOperation("Delete vendor's location")
    public void deleteLocation(@RequestParam(name = "id", required = true) @NotNull UUID id)  {
        log.debug(String.format("Deleting VendorLocation with ID %s", id));
        vendorLocationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("VendorLocation %s not found", id)));
        vendorLocationRepository.deleteById(id);
        log.debug(String.format("Successfully deleted VendorLocation with ID %s", id));
    }
}
