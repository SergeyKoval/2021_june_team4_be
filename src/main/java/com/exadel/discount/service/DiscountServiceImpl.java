package com.exadel.discount.service;

import com.exadel.discount.dto.DiscountDTO;
import com.exadel.discount.mapper.DiscountMapper;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.service.interfaces.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Override
    public DiscountDTO create(DiscountDTO discountDTO) {
        return discountMapper.getDTO(discountRepository.save(discountMapper.parseDTO(discountDTO)));
    }

    @Override
    public DiscountDTO get(UUID id) {
        return discountMapper.getDTO(discountRepository.findById(id).orElse(null));
    }

    @Override
    public List<DiscountDTO> getAll() {
        return discountMapper.getListDTO(discountRepository.findAll());
    }

    @Override
    public long count() {
        return discountRepository.count();
    }
}
