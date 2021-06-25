package com.exadel.discount.service.impl;

import com.exadel.discount.dto.DiscountDTO;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.DiscountMapper;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.service.interfaces.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;
    private final CountryRepository countryRepository;

    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        log.debug("Saving new Discount");
        Discount discount = discountRepository.save(discountMapper.parseDTO(discountDTO));
        log.debug("Successfully saved new Discount");
        return discountMapper.getDTO(discount);
    }

    @Override
    public DiscountDTO getById(UUID id) {
        log.debug(String.format("Finding Discount with ID %s", id));
        Discount discount = discountRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Discount with id %s not found",id)));
        log.debug(String.format("Successfully found Discount with ID %s", id));

        Set<VendorLocation> vendorLocations = new HashSet<>();
        for (VendorLocation v : discount.getVendorLocations()) {
            v.setCountry(countryRepository.findById(v.getCity().getCountry().getId()).orElse(null));
            vendorLocations.add(v);
        }
        discount.setVendorLocations(vendorLocations);
        return discountMapper.getDTO(discount);
    }

    @Override
    public List<DiscountDTO> getAll() {
        log.debug("Getting list of all Discounts");
        List<Discount> discounts = discountRepository.findAll();

        for (int i=0; i<discounts.size(); i++) {
            Set<VendorLocation> vendorLocations = new HashSet<>();
            for (VendorLocation v : discounts.get(i).getVendorLocations()) {
                v.setCountry(countryRepository.findById(v.getCity().getCountry().getId()).orElse(null));
                vendorLocations.add(v);
            }
            Discount discount = discounts.get(i);
            discount.setVendorLocations(vendorLocations);
            discounts.set(i, discount);
        }

        log.debug("Successfully got list of all Discounts");
        return discountMapper.getListDTO(discounts);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug(String.format("Deleting Discount with ID %s", id));
        discountRepository.deleteById(id);
        log.debug(String.format("Successfully deleted Discount with ID %s", id));
    }

}
