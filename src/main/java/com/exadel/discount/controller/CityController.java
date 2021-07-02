package com.exadel.discount.controller;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.CityService;
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
public class CityController {
    private final CityService cityService;

    @GetMapping("/cities")
    @ApiOperation("Get list of all cities")
    public List<CityDTO> getAllCities() {
        return cityService.findAll();
    }

    @GetMapping("{id}/cities")
    @ApiOperation("Get list of all cities by CountryID")
    public List<CityDTO> getAllCitiesByCountryId(@PathVariable("id") UUID id) {
        return cityService.findAllByCountryId(id);
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save new City")
    public CityDTO saveCity(@Validated(Create.class) @RequestBody CityDTO cityDTO) {
        return cityService.save(cityDTO);
    }

    @GetMapping("/cities/{id}")
    @ApiOperation("Get city by ID")
    public CityDTO getCityById(@PathVariable @NotNull final UUID id) {
        return cityService.findById(id);
    }

    @DeleteMapping("/cities/{id}")
    @ApiOperation("Delete city by ID")
    public void deleteCity(@PathVariable @NotNull final UUID id) {
        cityService.deleteById(id);
    }
}
