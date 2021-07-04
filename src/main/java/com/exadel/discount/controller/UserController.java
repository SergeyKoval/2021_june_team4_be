package com.exadel.discount.controller;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation("Get sorted page-list of all users without filtering")
    public List<UserCityDto> getallUsers(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(name = "sortDirection", defaultValue = "") String sortDirection,
                                         @RequestParam(name = "sortField", defaultValue = "id") String sortField) {
        return userService.findAllUsers(pageNumber, pageSize, sortDirection, sortField);
    }

    @GetMapping("/role")
    @ApiOperation("Get sorted page-list of users filtered by role")
    public List<UserCityDto> getUsersByRole(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(name = "sortDirection", defaultValue = "") String sortDirection,
                                            @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                            @RequestParam(name = "roleFilter", defaultValue = "USER") String roleFilter) {
        return userService.findUsersByRole(pageNumber, pageSize, sortDirection, sortField, roleFilter);
    }


    @GetMapping("/city")
    @ApiOperation("Get sorted page-list of users filtered by city")
    public List<UserCityDto> getUsersByCity(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(name = "sortDirection", defaultValue = "") String sortDirection,
                                            @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                            @RequestParam(name = "cityFilter", defaultValue = "") String cityFilter) {
        return userService.findUsersOfCity(pageNumber, pageSize, sortDirection, sortField, cityFilter);
    }

    @GetMapping("/country")
    @ApiOperation("Get sorted page-list of users filtered by country")
    public List<UserCityDto> getUserOfCountry(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                           @RequestParam(name = "sortDirection", defaultValue = "") String sortDirection,
                                           @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                           @RequestParam(name = "countryFilter", defaultValue = "") String countryFilter) {
        return userService.findUsersOfCountry(pageNumber, pageSize, sortDirection, sortField, countryFilter);
    }

    @GetMapping("{id}")
    @ApiOperation("Get user by ID")
    public UserCityDto getUserById(@PathVariable @NotNull final UUID id) {
        return userService.findUserById(id);
    }

    @GetMapping("/name")
    @ApiOperation("Get users by lastname and firstname")
    public List<UserDto> getUsersByName(@RequestParam("lastname") @NotNull String lastName,
                                        @RequestParam("firstname") @NotNull String firstName) {
        return userService.findUsersByName(lastName, firstName);
    }
}
