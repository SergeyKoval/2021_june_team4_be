package com.exadel.discount.service;

import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.repository.VendoLocationrRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.interfaces.VendorLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendoLocationrServiceImpl implements VendorLocationService {

    @Autowired
    private VendoLocationrRepository vendoLocationrRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public VendorLocation create(VendorLocation locationVendor) {
        return vendoLocationrRepository.save(locationVendor);
    }

    @Override
    public VendorLocation get(Long id) {
        return vendoLocationrRepository.getOne(id);
    }

    @Override
    public List<VendorLocation> getAll() {
        return vendoLocationrRepository.findAll();
    }

    @Override
    public long count() {
        return vendoLocationrRepository.count();
    }
}
