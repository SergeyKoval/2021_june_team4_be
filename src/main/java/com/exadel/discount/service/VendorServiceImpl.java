package com.exadel.discount.service;

import com.exadel.discount.dto.VendorDTO;
import com.exadel.discount.mapper.VendorMapper;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.interfaces.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public VendorDTO create(VendorDTO vendorDTO) {
        return vendorMapper.getDTO(vendorRepository.save(vendorMapper.parseDTO(vendorDTO)));
    }

    @Override
    public VendorDTO get(UUID id) {
        return vendorMapper.getDTO(vendorRepository.findById(id).orElse(null));
    }

    @Override
    public List<VendorDTO> getAll() {
        return vendorMapper.getListDTO(vendorRepository.findAll());
    }

    @Override
    public long count() {
        return vendorRepository.count();
    }
}
