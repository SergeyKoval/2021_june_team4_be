package com.exadel.discount.service;

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

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User expected;

    @BeforeEach
    public void setUp(){
        expected = new User();
        expected.setFirstName("Kim");
        expected.setLastName("Kardashian");
        expected.setEmail("kim@mail.com");
        expected.setPassword("admin");
        expected.setId(UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6"));
    }

    @AfterEach
    public void tearDown(){
        expected = null;
    }

    @Test
    public void testFindUserById(){
        when(userRepository.findById(UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6"))).thenReturn(Optional.of(expected));
        userMapper.toUserDTO(expected);
        UserDTO actual = userService.findUserById(UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6"));
        Assertions.assertEquals(userMapper.toUserDTO(expected),actual);
        verify(userRepository, times(1)).findById(UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6"));
    }
}
