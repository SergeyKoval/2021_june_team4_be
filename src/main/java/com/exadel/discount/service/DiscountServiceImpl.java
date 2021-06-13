package com.exadel.discount.service;

import com.exadel.discount.entity.Discount;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.service.interfaces.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public Discount create(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public Discount get(UUID id) {
        return discountRepository.getOne(id);
    }

    @Override
    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public long count() {
        return discountRepository.count();
    }
}
