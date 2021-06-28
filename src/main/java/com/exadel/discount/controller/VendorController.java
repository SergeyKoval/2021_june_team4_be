package com.exadel.discount.controller;

import com.exadel.discount.dto.VendorDTO;
import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.service.VendorLocationService;
import com.exadel.discount.service.VendorService;
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
    public VendorDTO saveNewVendor(@RequestBody @Validated(Create.class) VendorDTO vendor) {
        return vendorService.save(vendor);
    }

    @DeleteMapping
    @ApiOperation("Delete vendor by ID")
    public void deleteVendor(@RequestParam(name = "id", required = true) @NotNull UUID id) {
        vendorService.deleteById(id);
    }


    @GetMapping("/{vendorId}/locations")
    @ApiOperation("Get list of all vendor locations")
    public List<VendorLocationDTO> getVendorLocations(@PathVariable(name = "vendorId") @NotNull UUID id) {
        return vendorService.getById(id).getVendorLocations();
    }

    @GetMapping("/{vendorId}/locations/{locationId}")
    @ApiOperation("Get list of all vendor locations")
    public VendorLocationDTO getVendorLocationById(@PathVariable(name = "vendorId") @NotNull UUID vendotId,
                                                         @PathVariable(name = "locationId") @NotNull UUID locationId) {
        return vendorLocationService.getById(locationId);
    }

    @PostMapping("/{vendorId}/locations")
    @ApiOperation("Add new location of vendor")
    public VendorLocationDTO addVendorLocation(@PathVariable(name = "vendorId") @NotNull UUID vendorId,
                                          @RequestBody @Valid VendorLocationDTO location) {
        VendorLocationDTO vendorLocationDTO = vendorLocationService.save(location, vendorId);
        return vendorLocationDTO;
    }

    @DeleteMapping("/{vendorId}/locations/{locationId}")
    @ApiOperation("Delete vendor's location")
    public void deleteLocation(@PathVariable(name = "vendorId") @NotNull UUID vendorId,
                               @PathVariable(name = "locationId") @NotNull UUID locationId) throws Exception {
        vendorLocationService.deleteById(vendorId, locationId);
    }


}
