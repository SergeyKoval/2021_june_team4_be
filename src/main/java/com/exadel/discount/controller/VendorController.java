package com.exadel.discount.controller;

import com.exadel.discount.dto.location.CreateLocationDTO;
import com.exadel.discount.dto.location.LocationInfoDTO;
import com.exadel.discount.dto.vendor.BaseVendorDTO;
import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.DetailedVendorDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    private final List<DetailedVendorDTO> detailedVendorsServiceMock = List.of(
            new DetailedVendorDTO(UUID.randomUUID(), "Vendor1", Set.of(
                    new LocationInfoDTO(UUID.randomUUID(), "Ukraine", "Dnipro", "Some address", "Some contacts")
            ), "Description")
    );

    private final List<BaseVendorDTO> baseVendorsServiceMock = List.of(
            new BaseVendorDTO(UUID.randomUUID(), "Vendor2")
    );

    @GetMapping
    @ApiOperation("Get list of all vendors")
    public ResponseEntity<List<BaseVendorDTO>> getVendorsList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(baseVendorsServiceMock);
    }

    @GetMapping("/{vendorId}")
    @ApiOperation("Get vendor by ID")
    public ResponseEntity<DetailedVendorDTO> getVendorById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(detailedVendorsServiceMock.get(0));
    }

    @PostMapping
    @ApiOperation("Save new vendor")
    public void saveNewVendor(@RequestBody CreateVendorDTO vendor) {
        detailedVendorsServiceMock.add(new DetailedVendorDTO(UUID.randomUUID(), vendor.getName(), vendor.getLocations(), vendor.getDescription()));
    }

    @PutMapping("/{vendorId}")
    @ApiOperation("Update information about vendor")
    public void updateVendor(@PathVariable UUID vendorId, @RequestBody DetailedVendorDTO vendor) {
        detailedVendorsServiceMock.add(vendor);
    }

    @DeleteMapping("/{vendorId}")
    @ApiOperation("Delete vendor by ID")
    public void deleteVendor(@PathVariable UUID vendorId) {

    }

    @GetMapping("/{vendorId}/locations")
    @ApiOperation("Get list of all vendor locations")
    public ResponseEntity<List<LocationInfoDTO>> getVendorLocations(@PathVariable UUID vendorId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ArrayList<>());
    }

    @PostMapping("/{vendorId}/locations")
    @ApiOperation("Add new location of vendor")
    public void addNewLocation(@PathVariable UUID vendorId, @RequestBody CreateLocationDTO location) {

    }

    @PutMapping("/{vendorId}/locations/{locationId}")
    @ApiOperation("Update vendor location")
    public ResponseEntity<LocationInfoDTO> updateLocation(@PathVariable UUID id, @RequestBody LocationInfoDTO location) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(location);
    }

    @GetMapping("/location")
    @ApiOperation("Get list of vendors by location (country and city)")
    public ResponseEntity<List<BaseVendorDTO>> getVendorsByLocation(@RequestParam String country, @RequestParam String city) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(baseVendorsServiceMock);
    }
}
