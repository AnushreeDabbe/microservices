package com.stackroute.restServiceExample.repository;


import com.stackroute.restServiceExample.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenUserToSaveShouldReturnSavedUser(){
        User user = new User(1,"John","Thompson",25);
        userRepository.save(user);
        User user1 = userRepository.findById(user.getId()).get();
        assertNotNull(user1);
        assertEquals(user1.getFirstName(), user1.getFirstName());
    }

    @Test
    public void givenGivenGetAllUserThenShouldReturnList(){
        User user = new User(2,"Harry", "Styles", 26);
        User user1 = new User(3,"Hillary", "Clinton",50);
        userRepository.save(user);
        userRepository.save(user1);
        List<User> userList = (List<User>) userRepository.findAll();
        assertEquals("Hillary", userList.get(1).getFirstName());
    }
}
