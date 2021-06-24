package com.exadel.discount.controller;

import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
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
    public UserDto getUserById(@PathVariable final UUID id) {
        return userService.findUserById(id);
    }

    @GetMapping("/name")
    @ApiOperation("Get user by lastname and firstname")
    public List<UserDto> getUsersByName(@RequestParam("lastname") String lastName,
                                        @RequestParam("firstame") String firstName) {
        return userService.findUsersByName(lastName, firstName);
    }
}
