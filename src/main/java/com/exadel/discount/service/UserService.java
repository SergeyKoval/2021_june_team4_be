package com.exadel.discount.service;

import com.exadel.discount.model.dto.user.UserDTO;
import com.exadel.discount.model.dto.user.UserFilter;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserDTO findUserById(UUID id);

    List<UserDTO> search(int pageNumber, int pageSize, String sortDirection, String sortField,
                         UserFilter userfilter);
}
