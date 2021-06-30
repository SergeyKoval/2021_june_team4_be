package com.exadel.discount.controller;

import com.exadel.discount.dto.DiscountDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.DiscountMapper;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.service.DiscountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping
    @ApiOperation("Get all discounts")
    public List<DiscountDTO> getAllDiscounts() {
        return discountService.getAll();
    }

    @GetMapping("/{discountId}")
    @ApiOperation("Get discount by ID")
    public DiscountDTO getDiscountById(
            @PathVariable(name = "discountId") @NotNull UUID id) {
        return discountService.getById(id);
    }

    @PostMapping
    @ApiOperation("Add new discount")
    public DiscountDTO addDiscount(@RequestBody @Validated(Create.class) DiscountDTO discountDTO) {
        return discountService.save(discountDTO);
    }

    @DeleteMapping
    @ApiOperation("Delete discount")
    public void deleteDiscount(@RequestParam(name = "id", required = true) @NotNull UUID id) {
        discountService.deleteById(id);
    }



}