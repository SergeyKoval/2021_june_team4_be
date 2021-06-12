package com.exadel.discount.controller;

import com.exadel.discount.dto.UserDto;
import com.exadel.discount.entity.User;
import com.exadel.discount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody final UserDto userDto) {
        User user = userService.addUser(User.from(userDto));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.findAllUsers();
        List<UserDto> usersDto = users.stream().map(UserDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable final UUID id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable final UUID id) {
        User user = userService.deleteUser(id);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable final UUID id,
                                                @RequestBody final UserDto userDto) {
        User user = userService.editUser(id, User.from(userDto));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable String name) {
        List<User> users = userService.findUsersByName(name);
        List<UserDto> usersDto = users.stream().map(UserDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PostMapping("{personId}/order/{couponId}/add")
    public ResponseEntity<UserDto> addCouponToUser(@PathVariable final UUID userId,
                                                       @PathVariable final UUID couponId) {
        User user = userService.addCouponToUser(userId, couponId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @DeleteMapping("{userId}/coupon/{couponId}/remove")
    public ResponseEntity<UserDto> removeCouponFromUser(@PathVariable final UUID userId,
                                                            @PathVariable final UUID couponId) {
        User user = userService.removeCouponFromUser(userId, couponId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }
}
