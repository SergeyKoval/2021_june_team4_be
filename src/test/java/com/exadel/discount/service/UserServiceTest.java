package com.exadel.discount.service;

import com.exadel.discount.controller.AbstractIT;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.mapper.UserMapper;
import com.exadel.discount.model.dto.user.UserDTO;
import com.exadel.discount.model.entity.User;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;


import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
public class UserServiceTest extends AbstractIT {

    private static final UUID ID = UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6");

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User expected;

    @BeforeEach
    public void setUp() {
        expected = new User();
        expected.setFirstName("Kim");
        expected.setLastName("Kardashian");
        expected.setEmail("kim@mail.com");
        expected.setPassword("admin");
        expected.setId(ID);
    }

    @AfterEach
    public void tearDown() {
        expected = null;
    }

    @Test
    public void testFindUserById() {
        when(userRepository.findById(ID)).thenReturn(Optional.of(expected));
        userMapper.toUserDTO(expected);
        UserDTO result = userService.findUserById(ID);
        Assertions.assertEquals(userMapper.toUserDTO(expected), result);
        verify(userRepository, times(1)).findById(ID);
    }

    @Test
    public void testExceptionFindUserById() {
        UUID uuid = UUID.fromString("c21e8b1f-08d8-472d-a9b8-e4e295b9b605");
        Exception exception = assertThrows(NotFoundException.class,
                () -> userService.findUserById(uuid)
        );

        Assertions.assertEquals("User with id " + uuid + " not found", exception.getMessage());
        verify(userRepository, times(1)).findById(uuid);
    }
}
