package com.exadel.discount.service.interfaces;

import com.exadel.discount.dto.DiscountDTO;

import java.util.List;
import java.util.UUID;

public interface DiscountService {

    DiscountDTO save(DiscountDTO discountDTO);
    DiscountDTO getById(UUID id);
    List<DiscountDTO> getAll();
    void deleteById(UUID id);

}
