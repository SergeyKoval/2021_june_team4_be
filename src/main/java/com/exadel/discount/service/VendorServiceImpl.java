package com.exadel.discount.service;

import com.exadel.discount.entity.Vendor;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.interfaces.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public Vendor create(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor get(UUID id) {
        return vendorRepository.getOne(id);
    }

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @Override
    public long count() {
        return vendorRepository.count();
    }
}
