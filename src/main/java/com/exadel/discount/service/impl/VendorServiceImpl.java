package com.exadel.discount.service.impl;

import com.exadel.discount.dto.VendorDTO;
import com.exadel.discount.entity.Country;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.VendorMapper;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;
    //TODO
    //private final CountryRepository countryRepository;

    @Override
    public VendorDTO save(VendorDTO vendorDTO) {
        log.debug("Saving new Vendor");
        Vendor vendor = vendorRepository.save(vendorMapper.parseDTO(vendorDTO));
        log.debug("Successfully saved new Vendor");
        return vendorMapper.getDTO(vendor);
    }

    @Override
    public VendorDTO getById(UUID id) {
        log.debug(String.format("Finding Vendor with ID %s", id));
        Vendor vendor = vendorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s not found",id)));
        log.debug(String.format("Successfully found Vendor with ID %s", id));
        //TODO
        /*List<VendorLocation> vendorLocations = new ArrayList<>();
        for (VendorLocation v : vendor.getVendorLocations()) {
            v.setCountry(countryRepository.findById(v.getCity().getCountry().getId()).orElse(null));
            vendorLocations.add(v);
        }
        vendor.setVendorLocations(vendorLocations);*/
        return vendorMapper.getDTO(vendor);
    }

    @Override
    public List<VendorDTO> getAll() {
        log.debug("Getting list of all Vendors");
        List<Vendor> vendorList = vendorRepository.findAll();
        //TODO
        /*for (int i=0; i<vendorList.size(); i++) {
            List<VendorLocation> vendorLocations = new ArrayList<>();
            for (VendorLocation v : vendorList.get(i).getVendorLocations()) {
                v.setCountry(countryRepository.findById(v.getCity().getCountry().getId()).orElse(null));
                vendorLocations.add(v);
            }
            Vendor vendor = vendorList.get(i);
            vendor.setVendorLocations(vendorLocations);
            vendorList.set(i, vendor);
        }*/


        log.debug("Successfully got list of all Vendors");
        return vendorMapper.getListDTO(vendorList);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug(String.format("Deleting Vendor with ID %s", id));
        vendorRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s not found",id)));
        vendorRepository.deleteById(id);
        log.debug(String.format("Successfully deleted Vendor with ID %s", id));
    }

}
