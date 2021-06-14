package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.user.BaseUserDto;
import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.entity.Coupon;
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
    public ResponseEntity<UserDto> addUser(@RequestBody final BaseUserDto baseUserDto) {
        User user = userService.addUser(User.from(baseUserDto));
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
    public ResponseEntity<List<UserDto>> deleteUser(@PathVariable final UUID id) {
        List<User> remaindedUsers = userService.deleteUser(id);
        List<UserDto> remaindedUsersDto = remaindedUsers.stream().map(UserDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(remaindedUsersDto, HttpStatus.OK);
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

    @PostMapping("userid/{userId}")
    public ResponseEntity<UserDto> addCouponToUser(@RequestBody final CouponDto couponDto,
                                                   @PathVariable final UUID userId) {
        User user = userService.addNewCouponToUser(Coupon.from(couponDto), userId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @DeleteMapping("{userId}/couponout")
    public ResponseEntity<UserDto> removeCouponFromUser(@PathVariable final UUID userId,
                                                        @RequestBody final Coupon coupon) {
        User user = userService.removeCouponFromUser(userId, coupon);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }
}
