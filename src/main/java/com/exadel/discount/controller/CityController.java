package com.exadel.discount.controller;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.service.CityService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    @ApiOperation("Get list of all cities")
    public List<CityDTO> getAllCities() {
        return cityService.findAll();
    }

    @GetMapping("/country")
    @ApiOperation("Get list of all cities")
    public List<CityDTO> getAllCitiesByCountryName(@RequestParam("name") String countryName) {
        return cityService.findAllByCountry(countryName);
    }
    @GetMapping("/name")
    @ApiOperation("Get city by Name")
    public CityDTO getCityByName(@RequestParam("name") String name) {
        return cityService.findByName(name);
    }

    @PostMapping
    @ApiOperation("Save new City")
    public CityDTO saveCity(@Validated(Create.class) @RequestBody CityDTO cityDTO) {
        return cityService.save(cityDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get city by ID")
    public CityDTO getCityById(@PathVariable @NotNull final UUID id) {
        return cityService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete city by ID")
    public void deleteCity(@PathVariable @NotNull final UUID id) {
        cityService.deleteById(id);
    }
}
