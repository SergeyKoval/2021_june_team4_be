package com.exadel.discount.controller;

import com.exadel.discount.dto.DiscountDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.DiscountMapper;
import com.exadel.discount.repository.DiscountRepository;
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

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @GetMapping
    @ApiOperation("Get all discounts")
    public List<DiscountDTO> getAllDiscounts() {
        log.debug("Getting list of all Discounts");
        List<DiscountDTO> discountDTOS = discountMapper.getListDTO(discountRepository.findAll());
        log.debug("Successfully got list of all Discounts");
        return discountDTOS;
    }

    @GetMapping("/{discountId}")
    @ApiOperation("Get discount by ID")
    public DiscountDTO getDiscountById(
            @PathVariable(name = "discountId") @NotNull UUID id) {
        log.debug(String.format("Finding Discount with ID %s", id));
        Discount discount = discountRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Discount with id %s not found",id)));
        log.debug(String.format("Successfully found Discount with ID %s", id));
        return discountMapper.getDTO(discount);
    }

    @PostMapping
    @ApiOperation("Add new discount")
    public DiscountDTO addDiscount(@RequestBody @Validated(Create.class) DiscountDTO discountDTO) {
        log.debug("Saving new Discount");
        Discount discount = discountRepository.save(discountMapper.parseDTO(discountDTO));
        log.debug("Successfully saved new Discount");
        return discountMapper.getDTO(discount);
    }

    @DeleteMapping
    @ApiOperation("Delete discount")
    public void deleteDiscount(@RequestParam(name = "id", required = true) @NotNull UUID id) {
        log.debug(String.format("Deleting Discount with ID %s", id));
        discountRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Discount with ID %s not found",id)));
        discountRepository.deleteById(id);
        log.debug(String.format("Successfully deleted Discount with ID %s", id));
    }



}
