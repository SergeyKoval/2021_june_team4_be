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
            new DetailedVendorDTO(1, "Vendor1", List.of(
                    new LocationInfoDTO(3142342, "Country", "City", "Some address", "Some contacts")
            ), "Description")
    );

    private final List<BaseVendorDTO> baseVendorsServiceMock = List.of(
            new BaseVendorDTO(2, "Vendor2")
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
    public ResponseEntity<DetailedVendorDTO> getVendorById(@PathVariable(name = "vendorId") long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(detailedVendorsServiceMock.get(0));
    }

    @PostMapping
    @ApiOperation("Save new vendor")
    public ResponseEntity<DetailedVendorDTO> saveNewVendor(@RequestBody CreateVendorDTO vendor) {
        detailedVendorsServiceMock.add(new DetailedVendorDTO(135463, vendor.getName(), vendor.getLocations(), vendor.getDescription()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(detailedVendorsServiceMock.get(0));
    }

    @PutMapping("/{vendorId}")
    @ApiOperation("Update information about vendor")
    public ResponseEntity<DetailedVendorDTO> updateVendor(@PathVariable(name = "vendorId") long vendorId, @RequestBody DetailedVendorDTO vendor) {
        detailedVendorsServiceMock.add(vendor);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(detailedVendorsServiceMock.get(0));
    }

    @DeleteMapping("/{vendorId}")
    @ApiOperation("Delete vendor by ID")
    public void deleteVendor(@PathVariable(name = "vendorId") long vendorId) {

    }

    @GetMapping("/{vendorId}/locations")
    @ApiOperation("Get list of all vendor locations")
    public ResponseEntity<List<LocationInfoDTO>> getVendorLocations(@PathVariable(name = "vendorId") long vendorId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ArrayList<>());
    }

    @PostMapping("/{vendorId}/locations")
    @ApiOperation("Add new location of vendor")
    public ResponseEntity<LocationInfoDTO> addNewLocation(@PathVariable(name = "vendorId") long vendorId, @RequestBody CreateLocationDTO location) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new LocationInfoDTO(3142342, "Country", "City", "Some address", "Some contacts"));
    }

    @PutMapping("/{vendorId}/locations/{locationId}")
    @ApiOperation("Update vendor's location")
    public ResponseEntity<LocationInfoDTO> updateLocation(@PathVariable(name = "vendorId") long vendorId, @PathVariable(name = "locationId") long locationId, @RequestBody LocationInfoDTO location) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(location);
    }

    @DeleteMapping("/{vendorId}/locations/{locationId}")
    @ApiOperation("Update vendor's location")
    public void deleteLocation(@PathVariable(name = "vendorId") long id, @PathVariable(name = "locationId") long locationId) {

    }
}
