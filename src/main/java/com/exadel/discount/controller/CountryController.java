package com.exadel.discount.controller;

import com.exadel.discount.dto.CountryDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.CountryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    @ApiOperation("Get list of all countries")
    public List<CountryDTO> getAllCountries() {
        return countryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save new Country")
    public CountryDTO saveCountry(@Validated(Create.class) @RequestBody CountryDTO countryDTO) {
        return countryService.save(countryDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get country by ID")
    public CountryDTO getCountryById(@PathVariable @NotNull final UUID id) {
        return countryService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete country by ID")
    public void deleteCountry(@PathVariable @NotNull final UUID id) {
        countryService.deleteById(id);
    }
}
