package com.exadel.discount.service;

import com.exadel.discount.model.dto.discount.DiscountFilter;
import com.exadel.discount.model.dto.discount.CreateDiscountDTO;
import com.exadel.discount.model.dto.discount.DiscountDTO;

import java.util.List;
import java.util.UUID;

public interface DiscountService {

    DiscountDTO save(CreateDiscountDTO discountDTO);

    DiscountDTO getById(UUID id);

    List<DiscountDTO> getAll(String sortBy, String sortDir, Integer page, Integer size, DiscountFilter filter);

    void deleteById(UUID id);

    DiscountDTO restoreById(UUID id);
}
