package com.exadel.discount.service.impl;

import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.mapper.UserMapper;
import com.exadel.discount.model.dto.user.UserDTO;
import com.exadel.discount.model.dto.user.UserFilter;
import com.exadel.discount.model.entity.QUser;
import com.exadel.discount.model.entity.User;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.repository.query.QueryPredicateBuilder;
import com.exadel.discount.repository.query.SortPageUtil;
import com.exadel.discount.service.UserService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
        log.debug("Successfully User is found by ID");
        return userMapper.toUserDTO(user);
    }

    @Override
    public List<UserDTO> search(int pageNumber, int pageSize, String sortDirection, String sortField,
                                UserFilter userFilter) {
        Pageable paging = SortPageUtil.makePageable(pageNumber, pageSize, sortDirection, sortField);

        log.debug("Getting sorted page-list of  Users");

        Page<User> userList;
        if (preparePredicateForFindingAllUsers(userFilter) == null) {
            userList = userRepository.findAll(paging);
        } else {
            userList = userRepository.findAll(preparePredicateForFindingAllUsers(userFilter), paging);
            log.debug("Successfully sorted page-list of Users is got without filtering");
        }

        return userMapper.toUserDTOList(userList.toList());

    }

    private Predicate preparePredicateForFindingAllUsers(UserFilter userFilter) {
        return ExpressionUtils.and(
                QueryPredicateBuilder.init()
                        .append(userFilter.getCityIds(), QUser.user.city.id::in)
                        .append(userFilter.getCountryIds(), QUser.user.city.country.id::in)
                        .buildOr(),
                QueryPredicateBuilder.init()
                        .append(userFilter.getFirstName(), QUser.user.firstName::containsIgnoreCase)
                        .append(userFilter.getLastName(), QUser.user.lastName::containsIgnoreCase)
                        .buildAnd());
    }
}
