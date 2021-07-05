package com.exadel.discount.controller;

import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.VendorDTO;
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

    @GetMapping
    @ApiOperation("Get list of all vendors")
    public List<VendorDTO> getVendorsList() {
        return vendorService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get vendor by ID")
    public VendorDTO getVendorById(
            @PathVariable(name = "id") @NotNull UUID id) {
        return vendorService.getById(id);
    }

    @PostMapping
    @ApiOperation("Add new vendor")
    public VendorDTO saveNewVendor(@RequestBody @Valid CreateVendorDTO vendor) {
        return vendorService.save(vendor);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete vendor by ID")
    public void deleteVendor(@PathVariable(name = "id") @NotNull UUID id) {
        vendorService.deleteById(id);
    }

    @GetMapping("/archived")
    @ApiOperation("Get all archived Vendors")
    public List<VendorDTO> getAllArchivedVendors() {
        return vendorService.getAllArchived();
    }

    @PutMapping("/restore/{id}")
    @ApiOperation("Restore Vendor by ID")
    public VendorDTO restoreVendor(@PathVariable UUID id) {
        return vendorService.restoreById(id);
    }
}
