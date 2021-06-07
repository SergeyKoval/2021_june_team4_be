package com.exadel.discount.controller;

import com.exadel.discount.dto.location.CreateLocationDTO;
import com.exadel.discount.dto.location.LocationInfoDTO;
import com.exadel.discount.dto.vendor.BaseVendorDTO;
import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.DetailedVendorDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    private final List<DetailedVendorDTO> detailedVendorsServiceMock = List.of(
            new DetailedVendorDTO(2352434, "Vendor1", List.of(
                    new LocationInfoDTO(3142342, "Country", "City", "Some address", "Some contacts")
            ), "Description")
    );

    private final List<BaseVendorDTO> baseVendorsServiceMock = List.of(
            new BaseVendorDTO(3534534, "Vendor2")
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
    public ResponseEntity<DetailedVendorDTO> getVendorById(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(detailedVendorsServiceMock.get(0));
    }

    @PostMapping
    @ApiOperation("Save new vendor")
    public void saveNewVendor(@RequestBody CreateVendorDTO vendor) {
        detailedVendorsServiceMock.add(new DetailedVendorDTO(135463, vendor.getName(), vendor.getLocations(), vendor.getDescription()));
    }

    @PutMapping("/{vendorId}")
    @ApiOperation("Update information about vendor")
    public void updateVendor(@PathVariable long vendorId, @RequestBody DetailedVendorDTO vendor) {
        detailedVendorsServiceMock.add(vendor);
    }

    @DeleteMapping("/{vendorId}")
    @ApiOperation("Delete vendor by ID")
    public void deleteVendor(@PathVariable long vendorId) {

    }

    @GetMapping("/{vendorId}/locations")
    @ApiOperation("Get list of all vendor locations")
    public ResponseEntity<List<LocationInfoDTO>> getVendorLocations(@PathVariable long vendorId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ArrayList<>());
    }

    @PostMapping("/{vendorId}/locations")
    @ApiOperation("Add new location of vendor")
    public void addNewLocation(@PathVariable long vendorId, @RequestBody CreateLocationDTO location) {

    }

    @PutMapping("/{vendorId}/locations/{locationId}")
    @ApiOperation("Update vendor location")
    public ResponseEntity<LocationInfoDTO> updateLocation(@PathVariable long id, @RequestBody LocationInfoDTO location) {
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
