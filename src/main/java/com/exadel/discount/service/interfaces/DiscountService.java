package com.exadel.discount.service.interfaces;

import com.exadel.discount.entity.Discount;

import java.util.List;
import java.util.UUID;

public interface DiscountService {

    Discount create(Discount discount);
    Discount get(UUID id);
    List<Discount> getAll();
    long count();

}
