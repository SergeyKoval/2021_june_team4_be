package com.exadel.discount.controller;

import com.exadel.discount.dto.CountryDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.CountryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("Save new Country")
    public CountryDTO saveCountry(@Validated(Create.class) @RequestBody CountryDTO countryDTO) {
        return countryService.save(countryDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get country by ID")
    public CountryDTO getCountryById(@PathVariable UUID id) {
        return countryService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete country by ID")
    public void deleteCountry(@PathVariable UUID id) {
        countryService.deleteById(id);
    }

    @GetMapping("/")
    @ApiOperation("Get country by Name")
    public CountryDTO getCountryByName(@RequestParam("name") String name) {
        return countryService.findByName(name);
    }


}
