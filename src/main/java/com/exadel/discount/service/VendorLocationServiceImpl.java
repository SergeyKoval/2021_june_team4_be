package com.exadel.discount.service;

import com.exadel.discount.dto.VendorLocationDTO;
import com.exadel.discount.mapper.VendorLocationMapper;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.service.interfaces.VendorLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VendorLocationServiceImpl implements VendorLocationService {

    private final VendorLocationRepository vendorLocationRepository;
    private final VendorLocationMapper vendorLocationMapper;

    @Override
    public VendorLocationDTO create(VendorLocationDTO vendorLocationDTO) {
        return vendorLocationMapper.getDTO(vendorLocationRepository.save(vendorLocationMapper.parseDTO(vendorLocationDTO)));
    }

    @Override
    public VendorLocationDTO get(UUID id) {
        return vendorLocationMapper.getDTO(vendorLocationRepository.findById(id).orElse(null));
    }

    @Override
    public List<VendorLocationDTO> getAll() {
        return vendorLocationMapper.getListDTO(vendorLocationRepository.findAll());
    }

    @Override
    public long count() {
        return vendorLocationRepository.count();
    }
}
