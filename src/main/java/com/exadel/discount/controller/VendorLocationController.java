package com.exadel.discount.controller;

import com.exadel.discount.dto.location.CreateLocationDTO;
import com.exadel.discount.dto.location.LocationDTO;
import com.exadel.discount.entity.VendorLocation;
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

    private final VendorLocationService vendorLocationService;

    @GetMapping("/{vendorId}")
    @ApiOperation("Get all VendorLocations vendor's")
    List<LocationDTO> getAll(@PathVariable("vendorId") @NotNull UUID id) {
        return vendorLocationService.getAll(id);
    }

    @GetMapping
    @ApiOperation("Get location by ID")
    LocationDTO getById(@RequestParam(value = "id", required = true) @NotNull UUID id) {
        return vendorLocationService.getById(id);
    }


    @PostMapping
    @ApiOperation("Add new location")
    public CreateLocationDTO addVendorLocation( @RequestBody @Validated CreateLocationDTO vendorLocation) {
        return vendorLocationService.save(vendorLocation);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete location")
    public void deleteLocation(@PathVariable(name = "id") @NotNull UUID id) {
        vendorLocationService.deleteById(id);
    }
}
