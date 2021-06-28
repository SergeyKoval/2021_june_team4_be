package com.exadel.discount.controller;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation("Get list of all users with sorting")
    /**  Get list of all users with sorting by params
     sortDirection - ASC or DSC (unsorted - by default) ;
     sortField - name of sorted field by (id - by default)-firstName/lastName/phone/email/login/role/password **/
    public List<UserCityDto> getUsers(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(name = "sortDirection", defaultValue = "") String sortDirection,
                                      @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                      @RequestParam(name = "cityFilter", defaultValue = "") String cityFilter,
                                      @RequestParam(name = "countryFilter", defaultValue = "") String countryFilter,
                                      @RequestParam(name = "roleFilter", defaultValue = "") String roleFilter) {
        Sort sort = Sort.unsorted();

        if (!sortDirection.equals("")) {
            sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
        }
        return userService.findAllUsers(pageNumber, pageSize, sort, cityFilter, countryFilter, roleFilter);
    }

    @GetMapping("{id}")
    @ApiOperation("Get user by ID")
    public UserCityDto getUserById(@PathVariable final UUID id) {
        return userService.findUserById(id);
    }

    @GetMapping("/name")
    @ApiOperation("Get user by lastname and firstname")
    public List<UserDto> getUsersByName(@RequestParam("lastname") String lastName,
                                        @RequestParam("firstame") String firstName) {
        return userService.findUsersByName(lastName, firstName);
    }
}
