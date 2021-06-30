package com.exadel.discount.controller;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.VendorLocationMapper;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.service.VendorLocationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class VendorLocationController {

    private final VendorLocationService vendorLocationService;

    @GetMapping("/{vendorId}")
    @ApiOperation("Get all VendorLocations vendor's")
    List<VendorLocationDTO> getAll(@PathVariable("vendorId") @NotNull UUID id) {
        return vendorLocationService.getAll(id);
    }

    @GetMapping
    VendorLocationDTO getById(@RequestParam(value = "id", required = true) @NotNull UUID id) {
        return vendorLocationService.getById(id);
    }


    @PostMapping
    @ApiOperation("Add new location of vendor")
    public VendorLocationDTO addVendorLocation(@RequestParam(name = "vendorId", required = true) @NotNull UUID vendorId,
                                               @RequestBody @Validated VendorLocation vendorLocation) {
        return vendorLocationService.save(vendorLocation, vendorId);
    }

    @DeleteMapping
    @ApiOperation("Delete vendor's location")
    public void deleteLocation(@RequestParam(name = "id", required = true) @NotNull UUID id)  {
        vendorLocationService.deleteById(id);
    }
}
