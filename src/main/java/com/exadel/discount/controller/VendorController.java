package com.exadel.discount.controller;

import com.exadel.discount.dto.VendorDTO;
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
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    @GetMapping
    @ApiOperation("Get list of all vendors")
    public List<VendorDTO> getVendorsList(@RequestParam(name = "id", required = false) UUID id) {
        if (id == null) {
            return vendorService.getAll();
        } else {
            List<VendorDTO> vendors = new ArrayList<>();
            vendors.add(vendorService.getById(id));
            return vendors;
        }

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


}
