package com.exadel.discount.controller;

import com.exadel.discount.model.dto.user.UserDTO;
import com.exadel.discount.model.dto.user.UserFilter;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
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
    @ApiOperation("Get sorted page-list of all users with filtering with role USER - as default")
    @AdminAccess
    public List<UserDTO> getAllUsers(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                     @RequestParam(name = "sortDirection", defaultValue = "") String sortDirection,
                                     @RequestParam(name = "sortField", defaultValue = "id") String sortBy,
                                     @RequestParam(name = "cityName", required = false) List<UUID> cityIds,
                                     @RequestParam(name = "countryName", required = false) List<UUID> countryIds,
                                     @RequestParam(name = "firstName", required = false) String firstName,
                                     @RequestParam(name = "lastName", required = false) String lastName) {
        UserFilter filter = UserFilter.builder()
                .firstName(firstName)
                .lastName(lastName)
                .cityIds(cityIds)
                .countryIds(countryIds)
                .build();
        return userService.findAllUsers(pageNumber, pageSize, sortDirection, sortBy, filter);
    }

    @GetMapping("{id}")
    @ApiOperation("Get user by ID")
    @UserAccess
    public UserDTO getUserById(@PathVariable @NotNull final UUID id) {
        return userService.findUserById(id);
    }

    @GetMapping("/name")
    @ApiOperation("Get users by lastname and firstname")
    @AdminAccess
    public List<UserDTO> getUsersByName(@RequestParam("lastname") @NotNull String lastName,
                                        @RequestParam("firstname") @NotNull String firstName) {
        return userService.findUsersByName(lastName, firstName);
    }
}
