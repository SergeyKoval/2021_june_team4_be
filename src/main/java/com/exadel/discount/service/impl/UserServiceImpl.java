package com.exadel.discount.service.impl;

import com.exadel.discount.dto.user.UserDTO;
import com.exadel.discount.dto.user.UserFilter;
import com.exadel.discount.entity.QUser;
import com.exadel.discount.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.UserMapper;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.repository.query.QueryPredicateBuilder;
import com.exadel.discount.service.SortPageMaker;
import com.exadel.discount.service.UserService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO findUserById(UUID id) {
        log.debug("Finding User by ID");
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new NotFoundException(String.format("User with id %s not found", id));
        else log.debug("Successfully User is found by ID");
        return userMapper.toUserDTO(userOptional.get());
    }

    @Override
    public List<UserDTO> findAllUsers(int pageNumber,
                                      int pageSize,
                                      String sortDirection,
                                      String sortField,
                                      UserFilter filter) {
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);

        log.debug("Getting sorted page-list of  Users");

        Page<User> userList = userRepository.findAll(preparePredicateForFindingAll(filter), paging);
        log.debug("Successfully sorted page-list of Users is got without filtering");

        return userMapper.toUserDTOList(userList.toList());

    }

    @Override
    public List<UserDTO> findUsersByName(String lastName, String firstName) {
        log.debug("Finding User by lastName and firstName");
        List<User> suchNameUserList = userRepository.findDistinctByLastNameAndFirstName(lastName, firstName);
        if (suchNameUserList.size() == 0) throw
                new NotFoundException(String.format("Not found a user with lastname %s and " +
                        "                                                  firstname %s ", lastName, firstName));
        log.debug("Successfully User is found by lastname and firstname");
        return userMapper.toUserDTOList(suchNameUserList);
    }

    private Predicate preparePredicateForFindingAll(UserFilter userfilter) {
        return ExpressionUtils.and(
                QueryPredicateBuilder.init()
                        .append(userfilter.getCityName(), QUser.user.city.name::eq)
                        .append(userfilter.getCountryName(), QUser.user.city.country.name::eq)
                        .buildOr(),
                QueryPredicateBuilder.init()
                        .append(userfilter.getFirstName(), QUser.user.firstName::eq)
                        .append(userfilter.getLastName(), QUser.user.lastName::eq)
                        .append(userfilter.getRoleFilter().toUpperCase(Locale.ROOT), QUser.user.role.stringValue()::eq)
                        .buildAnd());
    }
}
