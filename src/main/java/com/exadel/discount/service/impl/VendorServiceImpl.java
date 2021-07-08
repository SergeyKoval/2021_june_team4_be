package com.exadel.discount.service.impl;

import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.VendorDTO;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.exception.DeletionRestrictedException;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.VendorMapper;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .findByIdAndArchived(id, false)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s not found", id)));
        log.debug(String.format("Successfully found Vendor with ID %s", id));
        return vendorMapper.getDTO(vendor);
    }

    @Override
    public List<VendorDTO> getAll() {
        log.debug("Getting list of all Vendors");
        List<Vendor> vendorList = vendorRepository.findAllByArchived(false);
        log.debug("Successfully got list of all Vendors");
        return vendorMapper.getListDTO(vendorList);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        log.debug(String.format("Deleting Vendor with ID %s", id));
        if (!vendorRepository.existsByIdWithNoDiscounts(id)) {
            throw new DeletionRestrictedException(
                    String.format("Vendor with ID %s doesn't exist or can't be deleted as it has discounts", id)
            );
        }
        vendorRepository.setArchivedById(id, true);
        log.debug(String.format("Successfully deleted Vendor with ID %s", id));
    }

    @Override
    public List<VendorDTO> getAllArchived() {
        log.debug("Getting list of archived Vendors");
        List<Vendor> vendorList = vendorRepository.findAllByArchived(true);
        log.debug("Successfully got list of archived Vendors");
        return vendorMapper.getListDTO(vendorList);
    }

    @Override
    @Transactional
    public VendorDTO restoreById(UUID id) {
        log.debug(String.format("Restoring Vendor with ID %s", id));
        if (!vendorRepository.existsByIdAndArchived(id, true)) {
            throw new NotFoundException(String.format("Archived Vendor with ID %s not found", id));
        }
        vendorRepository.setArchivedById(id, false);
        Vendor restoredVendor = vendorRepository
                .findByIdAndArchived(id, false)
                .orElseThrow(() -> new NotFoundException(String.format("Restored Vendor with ID %s not found", id)));
        log.debug(String.format("Successfully restored Vendor with ID %s", id));
        return vendorMapper.getDTO(restoredVendor);
    }
}