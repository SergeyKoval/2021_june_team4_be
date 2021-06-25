package com.exadel.discount.controller;

import com.exadel.discount.dto.VendorDTO;
import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.interfaces.VendorLocationService;
import com.exadel.discount.service.interfaces.VendorService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;
    private final VendorLocationService vendorLocationService;

    @GetMapping
    @ApiOperation("Get list of all vendors")
    public List<VendorDTO> getVendorsList() {
        return vendorService.getAll();
    }

    @GetMapping("/{vendorId}")
    @ApiOperation("Get vendor by ID")
    public VendorDTO getVendorById(
            @PathVariable(name = "vendorId") @NotNull UUID id) {
        return vendorService.getById(id);
    }

    @PostMapping
    @ApiOperation("Add new vendor")
    public VendorDTO saveNewVendor(
            @RequestBody @Validated(Create.class) VendorDTO vendor) {
        return vendorService.save(vendor);
    }

    @DeleteMapping("/{vendorId}")
    @ApiOperation("Delete vendor by ID")
    public void deleteVendor(
            @PathVariable(name = "vendorId") @NotNull UUID id) {
        vendorService.deleteById(id);
    }

    @GetMapping("/{vendorId}/locations")
    @ApiOperation("Get list of all vendor locations")
    public List<VendorLocationDTO> getVendorLocations(
            @PathVariable(name = "vendorId") @NotNull UUID id) {
        return vendorService.getById(id).getVendorLocations();
    }

    @PostMapping("/{vendorId}/locations")
    @ApiOperation("Add new location of vendor")
    public VendorLocationDTO addVendorLocation(
            @PathVariable(name = "vendorId") @NotNull UUID vendorId,
            @RequestBody @Validated(Create.class) VendorLocationDTO location) throws Exception {
        VendorLocationDTO vendorLocationDTO = vendorLocationService.save(location, vendorId);
        return vendorLocationDTO;
    }

    @DeleteMapping("/{vendorId}/locations/{locationId}")
    @ApiOperation("Delete vendor's location")
    public void deleteLocation(
            @PathVariable(name = "vendorId") @NotNull UUID vendorId,
            @PathVariable(name = "locationId") @NotNull UUID locationId) throws Exception {
        vendorLocationService.deleteById(vendorId, locationId);
    }
}
