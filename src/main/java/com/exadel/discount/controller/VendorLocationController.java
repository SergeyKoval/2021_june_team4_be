package com.exadel.discount.controller;

import com.exadel.discount.dto.location.CreateLocationDTO;
import com.exadel.discount.dto.location.LocationDTO;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class VendorLocationController {

    private final VendorLocationService vendorLocationService;

    @GetMapping
    @ApiOperation("Get all locations by Vendor's ID")
    List<LocationDTO> getAll(@RequestParam @NotNull UUID vendorId) {
        return vendorLocationService.getAll(vendorId);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get location by ID")
    LocationDTO getById(@PathVariable(name = "id") @NotNull UUID id) {
        return vendorLocationService.getById(id);
    }


    @PostMapping
    @ApiOperation("Add new location")
    public LocationDTO addVendorLocation(@RequestBody @Valid CreateLocationDTO vendorLocation) {
        return vendorLocationService.save(vendorLocation);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete location")
    public void deleteLocation(@PathVariable(name = "id") @NotNull UUID id) {
        vendorLocationService.deleteById(id);
    }
}
