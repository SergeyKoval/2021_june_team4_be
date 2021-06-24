package com.exadel.discount.service.impl;

import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.UserMapper;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findUserById(UUID id) {
        log.debug("Finding User by ID");

         User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
        log.debug("Successfully User is found by ID");

        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> findAllUsers() {
        log.debug("Getting list of all Users");
        List<User> userList = userRepository.findAll();
        log.debug("Successfully list of all Users is got");
        return userMapper.toUserDtoList(userList);
    }

    @Override
    public List<UserDto> findUsersByName(String lastName, String firstName) {
        log.debug("Finding User by lastname and firstname");

        List<User> suchNameUserList = userRepository.findDistinctByLastNameAndFirstName(lastName, firstName);
        if (suchNameUserList.size() == 0) throw new NotFoundException(String.format("Not found a user with Lastname %s and firstname %s ", lastName, firstName));
        log.debug("Successfully User is found by lastname and firstname");

        return userMapper.toUserDtoList(suchNameUserList);
    }
}
