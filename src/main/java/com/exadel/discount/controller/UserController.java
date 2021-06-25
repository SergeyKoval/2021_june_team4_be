package com.exadel.discount.controller;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation("Get list of all users")
    public List<UserDto> getUsers() {
        return userService.findAllUsers();
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
