package com.exadel.discount.controller;

import com.exadel.discount.dto.discount.CreateDiscountDTO;
import com.exadel.discount.dto.discount.DiscountDTO;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.DiscountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @UserAccess
    public List<DiscountDTO> getAllDiscounts() {
        return discountService.getAll();
    }

    @GetMapping("/{discountId}")
    @ApiOperation("Get discount by ID")
    @UserAccess
    public DiscountDTO getDiscountById(@PathVariable(name = "discountId") @NotNull UUID id) {
        return discountService.getById(id);
    }

    @PostMapping
    @ApiOperation("Add new discount")
    @AdminAccess
    public DiscountDTO addDiscount(@RequestBody @Valid CreateDiscountDTO discountDTO) {
        return discountService.save(discountDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete discount")
    @AdminAccess
    public void deleteDiscount(@PathVariable(name = "id") @NotNull UUID id) {
        discountService.deleteById(id);
    }

    @GetMapping("/archived")
    @ApiOperation("Get all archived Discounts")
    @AdminAccess
    public List<DiscountDTO> getAllArchivedDiscounts() {
        return discountService.getAllArchived();
    }

    @PutMapping("/archived/{id}/restore")
    @ApiOperation("Restore Discount by ID")
    @AdminAccess
    public DiscountDTO restoreDiscount(@PathVariable UUID id) {
        return discountService.restoreById(id);
    }
}
