package com.exadel.discount.controller;

import com.exadel.discount.dto.DiscountDTO;
import com.exadel.discount.dto.VendorDTO;
import com.exadel.discount.service.DiscountServiceImpl;
import com.exadel.discount.service.interfaces.DiscountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping
    @ApiOperation("Get all discounts")
    public List<DiscountDTO> getAllDiscount() {
        return discountService.getAll();
    }

    @GetMapping("/{discountId}")
    @ApiOperation("Get discount by ID")
    public DiscountDTO getDiscountById(@PathVariable(name = "discountId") @NotNull UUID id) {
        return discountService.get(id);
    }

    @PostMapping
    @ApiOperation("Add new discount")
    public DiscountDTO addDiscount(@RequestBody @Valid DiscountDTO discountDTO) {
        return discountService.save(discountDTO);
    }



}
