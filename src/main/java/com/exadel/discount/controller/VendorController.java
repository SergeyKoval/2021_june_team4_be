package com.exadel.discount.controller;

import com.exadel.discount.dto.VendorDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.VendorMapper;
import com.exadel.discount.repository.VendorRepository;
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
@RequestMapping("/vendors")
public class VendorController {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @GetMapping
    @ApiOperation("Get list of all vendors")
    public List<VendorDTO> getVendorsList() {
        log.debug("Getting list of all Vendors");
        List<Vendor> vendorList = vendorRepository.findAll();
        log.debug("Successfully got list of all Vendors");
        return vendorMapper.getListDTO(vendorList);
    }

    @GetMapping("/{vendorId}")
    @ApiOperation("Get vendor by ID")
    public VendorDTO getVendorById(
            @PathVariable(name = "vendorId") @NotNull UUID id) {
        log.debug(String.format("Finding Vendor with ID %s", id));
        Vendor vendor = vendorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s not found",id)));
        log.debug(String.format("Successfully found Vendor with ID %s", id));
        return vendorMapper.getDTO(vendor);
    }

    @PostMapping
    @ApiOperation("Add new vendor")
    public VendorDTO saveNewVendor(@RequestBody @Validated(Create.class) VendorDTO vendor) {
        log.debug("Saving new Vendor");
        Vendor savedVendor = vendorRepository.save(vendorMapper.parseDTO(vendor));
        log.debug("Successfully saved new Vendor");
        return vendorMapper.getDTO(savedVendor);
    }

    @DeleteMapping
    @ApiOperation("Delete vendor by ID")
    public void deleteVendor(@RequestParam(name = "id", required = true) @NotNull UUID id) {
        log.debug(String.format("Deleting Vendor with ID %s", id));
        vendorRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s not found",id)));
        vendorRepository.deleteById(id);
        log.debug(String.format("Successfully deleted Vendor with ID %s", id));
    }

}
