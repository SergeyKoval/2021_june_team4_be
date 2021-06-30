package com.exadel.discount.service.impl;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.entity.Role;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.UserCityMapper;
import com.exadel.discount.mapper.UserMapper;
import com.exadel.discount.repository.CityRepository;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final UserMapper userMapper;
    private final UserCityMapper userCityMapper;

    @Override
    public UserCityDto findUserById(UUID id) {
        log.debug("Finding User by ID");

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
        log.debug("Successfully User is found by ID and starting userCityDto creation");

        UserCityDto userCityDto = userCityMapper.toUserCityDto(user, user.getCity(), user.getCity().getCountry());
        log.debug("Successfully created userCityDto");

        return userCityDto;
    }

    @Override
    public List<UserCityDto> findAllUsers(int pageNumber, int pageSize, String sortDirection, String sortField) {
        Pageable paging = getPaging(pageNumber, pageSize, sortDirection, sortField);

        log.debug("Getting sorted page-list of  Users");

        Page<User> userList = userRepository.findAll(paging);
        log.debug("Successfully sorted page-list of Users is got without filtering");

        return toUserCityDtoList(userList);
    }

    @Override
    public List<UserCityDto> findUsersByRole(int pageNumber, int pageSize, String sortDirection, String sortField, String roleFilter) {
        Pageable paging = getPaging(pageNumber, pageSize, sortDirection, sortField);
        Page<User> userList;
        log.debug("Getting sorted page-list of Users by role");
        List<String> enumValues = List.of(Arrays.toString(Role.values()));
        if (enumValues.stream().anyMatch(e -> e.equals(roleFilter.toUpperCase()))) {
            userList = userRepository.roleSearch(Role.valueOf(roleFilter.toUpperCase()), paging);
        } else
            throw new NotFoundException(String.format("No User with role %s is found", roleFilter));

        log.debug("Successfully got filtered page-list of Users by role is got");
        return toUserCityDtoList(userList);
    }

    @Override
    public List<UserCityDto> findUsersOfCity(int pageNumber, int pageSize, String sortDirection, String sortField, String cityFilter) {
        Pageable paging = getPaging(pageNumber, pageSize, sortDirection, sortField);
        Page<User> userList;
        log.debug("Getting sorted page-list of Users by role");

        if (cityRepository.findByName(cityFilter).isPresent()) {
            userList = userRepository.citySearch(cityFilter, paging);
        } else
            throw new NotFoundException(String.format("No User from city %s is found", cityFilter));


        log.debug("Successfully got filtered page-list of Users by city is got");
        return toUserCityDtoList(userList);
    }


    @Override
    public List<UserCityDto> findUsersOfCountry(int pageNumber, int pageSize, String sortDirection, String sortField, String countryFilter) {
        Pageable paging = getPaging(pageNumber, pageSize, sortDirection, sortField);
        Page<User> userList;
        log.debug("Getting sorted page-list of Users by Country(");
        if (countryRepository.findByName(countryFilter).isPresent()) {
            userList = userRepository.countrySearch(countryFilter, paging);
        } else
            throw new NotFoundException(String.format("No User with Country %s is found", countryFilter));
        log.debug("Successfully got filtered page-list of Users by role is got");
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

    private Pageable getPaging(int pageNumber, int pageSize, String sortDirection, String sortField) {
        Sort sort = Sort.unsorted();
        if (!sortDirection.equals("")) {
            sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
        }
        return PageRequest.of(pageNumber - 1, pageSize, sort);
    }


    List<UserCityDto> toUserCityDtoList(Page<User> pageUserList) {
        return pageUserList.toList().stream()
                .map(e -> userCityMapper.toUserCityDto(e, e.getCity(), e.getCity().getCountry()))
                .collect(Collectors.toList());
    }

}
