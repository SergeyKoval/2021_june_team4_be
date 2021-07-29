package com.exadel.discount.controller;

import com.exadel.discount.model.dto.location.CreateLocationDTO;
import com.exadel.discount.model.dto.location.LocationDTO;
import com.exadel.discount.model.dto.location.UpdateLocationDTO;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.VendorLocationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class VendorLocationController {

    private final VendorLocationService vendorLocationService;

    @GetMapping
    @ApiOperation("Get all locations by Vendor's ID")
    @UserAccess
    List<LocationDTO> getAll(@RequestParam @NotNull UUID vendorId) {
        return vendorLocationService.getLocationsByVendorId(vendorId);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get location by ID")
    @UserAccess
    LocationDTO getById(@PathVariable(name = "id") @NotNull UUID id) {
        return vendorLocationService.getById(id);
    }

    @PostMapping
    @ApiOperation("Add new location")
    @AdminAccess
    public LocationDTO addVendorLocation(@RequestBody @Valid CreateLocationDTO vendorLocation) {
        return vendorLocationService.save(vendorLocation);
    }

    @PutMapping("/{id}")
    @AdminAccess
    @ApiOperation("Edit location by ID")
    public LocationDTO updateLocation(@PathVariable @NotNull UUID id,
                                      @RequestBody @NotNull UpdateLocationDTO locationDTO) {
        return vendorLocationService.updateLocationById(locationDTO, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete location")
    @AdminAccess
    public void deleteLocation(@PathVariable(name = "id") @NotNull UUID id) {
        vendorLocationService.deleteById(id);
    }
}
