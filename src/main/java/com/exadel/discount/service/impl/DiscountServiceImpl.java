package com.exadel.discount.service.impl;

import com.exadel.discount.dto.DiscountDTO;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.DiscountMapper;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.service.interfaces.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

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
        return discountMapper.getDTO(discount);
    }

    @Override
    public List<DiscountDTO> getAll() {
        log.debug("Getting list of all Discounts");
        List<Discount> discounts = discountRepository.findAll();
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
