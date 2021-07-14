package com.exadel.discount.controller;

import com.exadel.discount.model.dto.user.UserDTO;
import com.exadel.discount.model.dto.user.UserFilter;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.security.annotation.UserAccess;
import com.exadel.discount.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation("Get sorted page-list of all users with filtering with role USER - as default")
    @AdminAccess
    public List<UserDTO> getAllUsers(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                     @RequestParam(required = false, defaultValue = "10") int pageSize,
                                     @RequestParam(required = false, defaultValue = "") String sortDirection,
                                     @RequestParam(required = false, defaultValue = "id") String sortBy,
                                     @RequestParam(required = false) List<UUID> cityIds,
                                     @RequestParam(required = false) List<UUID> countryIds,
                                     @RequestParam(required = false) String firstName,
                                     @RequestParam(required = false) String lastName) {
        UserFilter filter = UserFilter.builder()
                .firstName(firstName)
                .lastName(lastName)
                .cityIds(cityIds)
                .countryIds(countryIds)
                .build();
        return userService.search(pageNumber, pageSize, sortDirection, sortBy, filter);
    }

    @GetMapping("{id}")
    @ApiOperation("Get user by ID")
    @UserAccess
    public UserDTO getUserById(@PathVariable @NotNull final UUID id) {
        return userService.findUserById(id);
    }
}
