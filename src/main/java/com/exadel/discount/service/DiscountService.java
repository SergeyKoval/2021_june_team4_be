package com.exadel.discount.service;

import com.exadel.discount.dto.discount.CreateDiscountDTO;
import com.exadel.discount.dto.discount.DiscountDTO;

import java.util.List;
import java.util.UUID;

public interface DiscountService {

    DiscountDTO save(CreateDiscountDTO discountDTO);

    DiscountDTO getById(UUID id);

    List<DiscountDTO> getAll();

    void deleteById(UUID id);

    List<DiscountDTO> getAllArchived();

    DiscountDTO restoreById(UUID id);
}
