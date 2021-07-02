package com.exadel.discount.controller;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.dto.CountryDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.CityService;
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
    private final CityService cityService;

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

    @GetMapping("/{countryId}")
    @ApiOperation("Get country by ID")
    public CountryDTO getCountryById(@PathVariable @NotNull final UUID countryId) {
        return countryService.findById(countryId);
    }

    @DeleteMapping("/{countryId}")
    @ApiOperation("Delete country by Id")
    public void deleteCountry(@PathVariable @NotNull final UUID countryId) {
        countryService.deleteById(countryId);
    }

    @GetMapping("{countryId}/cities")
    @ApiOperation("Get list of all cities by CountryId")
    public List<CityDTO> getAllCitiesByCountryId(@PathVariable UUID countryId) {
        return cityService.findAllByCountryId(countryId);
    }

    @PostMapping("{countryId}/cities")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save new City")
    public CityDTO saveCity(@Validated(Create.class) @RequestBody CityDTO cityDTO) {
        return cityService.save(cityDTO);
    }


    @GetMapping("{countryId}/cities/{cityId}")
    @ApiOperation("Get city by Id")
    public CityDTO getCityById(@PathVariable @NotNull final UUID cityId) {
        return cityService.findById(cityId);
    }

    @DeleteMapping("{countryId}/cities/{cityId}")
    @ApiOperation("Delete city by ID")
    public void deleteCity(@PathVariable @NotNull final UUID cityId) {
        cityService.deleteById(cityId);
    }


}
