package com.stackroute.restServiceExample.service;

import com.stackroute.restServiceExample.model.User;
import com.stackroute.restServiceExample.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    private List<User> userList;

    @Test
    public void givenUserToSaveShouldReturnSavedUser(){
        User user = new User(1,"John", "Thompson", 26);
        when(userRepository.save(any())).thenReturn(user);
         userService.saveUser(user);
        verify(userRepository,times(1)).save(any());
    }

    @Test
    public void givenGivenGetAllUserThenShouldReturnList(){
        userRepository.save(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> userList = userService.getAllUser();
        assertEquals(userList, userList);
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).findAll();
    }
}
