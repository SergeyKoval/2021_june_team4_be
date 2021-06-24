package com.exadel.discount.controller;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.interfaces.VendorLocationService;
import com.exadel.discount.service.interfaces.VendorService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class VendotLocationController {

    private final VendorService vendorService;
    private final VendorLocationService vendorLocationService;

    @GetMapping()
    @ApiOperation("Get list of all vendor locations")
    public List<VendorLocationDTO> getVendorLocations(
            @RequestParam(name = "vendorId", required = true) @NotNull UUID vendorId,
            @RequestParam(name = "locationId", required = false) UUID locationId) {
        if (locationId == null) {
            return vendorService.getById(vendorId).getVendorLocations();
        } else {
            List<VendorLocationDTO> vendorLocations = new ArrayList<>();
            vendorLocations.add(vendorLocationService.getById(locationId));
            return vendorLocations;
        }
    }

    @PostMapping()
    @ApiOperation("Add new location of vendor")
    public VendorLocationDTO addVendorLocation(
            @RequestParam(name = "vendorId", required = true) @NotNull UUID vendorId,
            @RequestBody @Validated(Create.class) VendorLocationDTO location) {
        return vendorLocationService.save(location, vendorId);
    }

    @DeleteMapping()
    @ApiOperation("Delete vendor's location")
    public void deleteLocation(
            @RequestParam(name = "vendorId", required = true) @NotNull UUID vendorId,
            @RequestParam(name = "locationId", required = true) @NotNull UUID locationId) {
        vendorLocationService.deleteById(vendorId, locationId);
    }
}
