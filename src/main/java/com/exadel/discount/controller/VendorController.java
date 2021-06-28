package com.exadel.discount.controller;

import com.exadel.discount.dto.VendorDTO;
import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.interfaces.VendorLocationService;
import com.exadel.discount.service.interfaces.VendorService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin
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
//    @PutMapping("/{vendorId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ApiOperation("Update information about vendor")
//    public DetailedVendorDTO updateVendor(@PathVariable(name = "vendorId") @Min(value = 0) long vendorId,
//                                          @RequestBody @Valid DetailedVendorDTO vendor) {
//        return vendor;
//    }

    @DeleteMapping("/{vendorId}")
    @ApiOperation("Delete vendor by ID")
    public void deleteVendor(
            @PathVariable(name = "vendorId") @NotNull UUID id) {
        vendorService.deleteById(id);
    }

//    @GetMapping("/{vendorId}/locations")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ApiOperation("Get list of all vendor locations")
//    public List<VendorLocationDTO> getVendorLocations(
//            @PathVariable(name = "vendorId") @NotNull UUID id) {
//        return vendorService.getById(id).getVendorLocations();
//    }

    @PostMapping("/{vendorId}/locations")
    @ApiOperation("Add new location of vendor")
    public VendorLocationDTO addVendorLocation(
            @PathVariable(name = "vendorId") @NotNull UUID vendorId,
            @RequestBody @Validated(Create.class) VendorLocationDTO location) throws Exception {
        VendorLocationDTO vendorLocationDTO = vendorLocationService.save(location, vendorId);
        return vendorLocationDTO;
    }
//    public LocationInfoDTO addNewLocation(@PathVariable(name = "vendorId") @Min(value = 0) long vendorId,
//                                          @RequestBody @Valid CreateLocationDTO location) {
//        return new LocationInfoDTO(3142342, "Country", "City", "Some address", "Some contacts");
//    }

//    @PutMapping("/{vendorId}/locations/{locationId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ApiOperation("Update vendor's location")
//    public LocationInfoDTO updateLocation(@PathVariable(name = "vendorId") @Min(value = 0) long vendorId,
//                                          @PathVariable(name = "locationId") @Min(value = 0) long locationId,
//                                          @RequestBody @Valid LocationInfoDTO location) {
//        return location;
//    }

//    @DeleteMapping("/{vendorId}/locations/{locationId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ApiOperation("Delete vendor's location")
//    public void deleteLocation(
//            @PathVariable(name = "vendorId") @NotNull UUID vendorId,
//            @PathVariable(name = "locationId") @NotNull UUID locationId) throws Exception {
//        vendorLocationService.deleteById(vendorId, locationId);
//    }
}
