package com.exadel.discount.service.impl;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.entity.Role;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.UserCityMapper;
import com.exadel.discount.mapper.UserMapper;
import com.exadel.discount.repository.CityRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.SortPageMaker;
import com.exadel.discount.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final UserMapper userMapper;
    private final UserCityMapper userCityMapper;

    @Override
    public UserCityDto findUserById(UUID id) {
        log.debug("Finding User by ID");

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new NotFoundException(String.format("User with id %s not found", id));
        else
            log.debug("Successfully User is found by ID and starting userCityDto creation");
        User user = userOptional.get();
        UserCityDto userCityDto = userCityMapper.toUserCityDto(user, user.getCity());
        log.debug("Successfully created userCityDto");

        return userCityDto;
    }

    @Override
    public List<UserCityDto> findAllUsers(int pageNumber, int pageSize, String sortDirection, String sortField) {
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);

        log.debug("Getting sorted page-list of  Users");

        Page<User> userList = userRepository.findAll(paging);
        log.debug("Successfully sorted page-list of Users is got without filtering");

        return toUserCityDtoList(userList);
    }

    @Override
    public List<UserCityDto> findUsersByRole(int pageNumber, int pageSize, String sortDirection, String sortField, String roleFilter) {
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);
        Page<User> userList;
        log.debug("Getting sorted page-list of Users by role");

        if (Stream.of(Role.values()).anyMatch(e -> e.toString().equals(roleFilter.toUpperCase()))) {
            userList = userRepository.findUserByRole(Role.valueOf(roleFilter.toUpperCase()), paging);
        } else
            throw new NotFoundException(String.format("No User with role %s is found", roleFilter));

        log.debug("Successfully got filtered page-list of Users by role is got");
        return toUserCityDtoList(userList);
    }

    @Override
    public List<UserCityDto> findUsersOfCity(int pageNumber, int pageSize, String sortDirection, String sortField, String cityFilter) {
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);
        Page<User> userList;
        log.debug("Getting sorted page-list of Users by city");

        if (cityRepository.findByName(cityFilter).isPresent()) {
            userList = userRepository.findUsersByCity_Name(cityFilter, paging);
        } else
            throw new NotFoundException(String.format("No User from city %s is found", cityFilter));


        log.debug("Successfully got filtered page-list of Users by city is got");
        return toUserCityDtoList(userList);
    }


    @Override
    public List<UserCityDto> findUsersOfCountry(int pageNumber, int pageSize, String sortDirection, String sortField, String countryFilter) {
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);
        log.debug("Getting sorted page-list of Users by country");

        Page<User> userList = userRepository.findUsersByCountry_Name(countryFilter, paging);
        if(userList.isEmpty()){
            throw new NotFoundException(String.format("No users from country %s is found", countryFilter));
        }
        log.debug("Successfully got filtered page-list of Users by country is got");
        return toUserCityDtoList(userList);
    }


    @Override
    public List<UserDto> findUsersByName(String lastName, String firstName) {
        log.debug("Finding User by lastName and firstName");

        List<User> suchNameUserList = userRepository.findDistinctByLastNameAndFirstName(lastName, firstName);
        if (suchNameUserList.size() == 0)
            throw new NotFoundException(String.format("Not found a user with Lastname %s and firstname %s ", lastName, firstName));
        log.debug("Successfully User is found by lastname and firstname");

        return userMapper.toUserDtoList(suchNameUserList);
    }

    private List<UserCityDto> toUserCityDtoList(Page<User> pageUserList) {
        return pageUserList.toList().stream()
                .map(e -> userCityMapper.toUserCityDto(e, e.getCity()))
                .collect(Collectors.toList());
    }
}
