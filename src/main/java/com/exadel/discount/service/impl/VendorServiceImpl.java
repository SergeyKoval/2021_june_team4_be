package com.exadel.discount.service.impl;

import com.exadel.discount.dto.vendor.BaseVendorDTO;
import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.VendorDTO;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.VendorMapper;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public VendorDTO save(CreateVendorDTO vendorDTO) {
        log.debug("Saving new Vendor");
        Vendor savedVendor = vendorRepository.save(vendorMapper.parseDTO(vendorDTO));
        log.debug("Successfully saved new Vendor");
        return vendorMapper.getDTO(savedVendor);
    }

    @Override
    public VendorDTO getById(UUID id) {
        log.debug(String.format("Finding Vendor with ID %s", id));
        Vendor vendor = vendorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s not found", id)));
        log.debug(String.format("Successfully found Vendor with ID %s", id));
        return vendorMapper.getDTO(vendor);
    }

    @Override
    public List<VendorDTO> getAll() {
        log.debug("Getting list of all Vendors");
        List<Vendor> vendorList = vendorRepository.findAll();
        log.debug("Successfully got list of all Vendors");
        return vendorMapper.getListDTO(vendorList);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug(String.format("Deleting Vendor with ID %s", id));
        vendorRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s not found", id)));
        vendorRepository.deleteById(id);
        log.debug(String.format("Successfully deleted Vendor with ID %s", id));
    }

    @Override
    public BaseVendorDTO updateVendorById(BaseVendorDTO vendorDTO, UUID id) {
        return vendorRepository.findById(id)
                .map( vendor -> {
                    if (vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }
                    if (vendorDTO.getContacts() != null) {
                        vendor.setContacts(vendorDTO.getContacts());
                    }
                    if (vendorDTO.getDescription() != null) {
                        vendor.setDescription(vendorDTO.getDescription());
                    }
                    return vendorMapper.getBaseDTO(vendorRepository.save(vendor));
                }).orElseThrow( () -> new NotFoundException(String.format("Vendor with ID %s not found", id)));
    }
}