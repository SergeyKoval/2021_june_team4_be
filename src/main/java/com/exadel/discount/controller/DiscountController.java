package com.exadel.discount.controller;

import com.exadel.discount.dto.DiscountDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.interfaces.DiscountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    /*@GetMapping
    @ApiOperation("Get all discounts")
    public List<DiscountDTO> getAllDiscount() {
        return discountService.getAll();
    }*/

    @GetMapping()
    @ApiOperation("Get discount by ID or all discounts")
    public List<DiscountDTO> getDiscountById(@RequestParam (name = "id", required = false) UUID id) {
        if (id == null) {
            return discountService.getAll();
        } else {
            List<DiscountDTO> discounts = new ArrayList<>();
            discounts.add(discountService.getById(id));
            return discounts;
        }
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
