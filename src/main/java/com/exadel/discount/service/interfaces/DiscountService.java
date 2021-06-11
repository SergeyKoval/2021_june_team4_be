package com.exadel.discount.service.interfaces;

import com.exadel.discount.entity.Discount;

import java.util.List;

public interface DiscountService {

    Discount create(Discount discount);
    Discount get(long id);
    List<Discount> getAll();
    long count();

}
