package com.exadel.discount.service.impl;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.UserCityMapper;
import com.exadel.discount.mapper.UserMapper;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserCityMapper userCityMapper;

    @Override
    public UserCityDto findUserById(UUID id) {
        log.debug("Finding User by ID");

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
        log.debug("Successfully User is found by ID and startig userCityDto creation");

        UserCityDto userCityDto = userCityMapper.toUserCityDto(user, user.getCity(), user.getCity().getCountry());
        log.debug("Successfully created userCityDto");

        return userCityDto;
    }

    @Override
    public List<UserCityDto> findAllUsers(int pageNumber, int pageSize, String sortDirection, String sortField, String cityFilter, String countryFilter, String roleFilter) {
        Sort sort = Sort.unsorted();

        if (!sortDirection.equals("")) {
            sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
        }
        log.debug("Getting sorted page-list of  Users");
        Page<User> userList = userRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
        log.debug("Getting sorted page-list of Users is got and filtering is starting");
        List<User> PageUserList = userList.toList();
        List<UserCityDto> filteredUserList = PageUserList.stream()
              //  .filter(e -> e.getCity().toString().equals(cityFilter))
                .filter(e -> cityFilter.equals("") || e.getCity().getName().contains(cityFilter))
                .filter(e -> countryFilter.equals("") || e.getCity().getCountry().getName().contains(countryFilter))
                .filter(e -> roleFilter.equals("") || e.getRole().toString().equals(roleFilter.toUpperCase()))
                .map(e -> userCityMapper.toUserCityDto(e, e.getCity(), e.getCity().getCountry()))
                .collect(Collectors.toList());

//        if (roleFilter.toUpperCase().equals("ADMIN") || roleFilter.toUpperCase().equals("ROLE")) {
//            filteredUserList = filteredUserList.stream().filter(e -> e.getRole().toString().equals(roleFilter.toUpperCase()))
//                    .collect(Collectors.toList());
//        }

        log.debug("Successfully filtered list of all Users is got");
        return filteredUserList;
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
}
