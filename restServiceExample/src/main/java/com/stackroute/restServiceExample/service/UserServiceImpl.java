package com.stackroute.restServiceExample.service;


import com.stackroute.restServiceExample.model.User;
import com.stackroute.restServiceExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "user")//it tells the sting where to store the cache for the class
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(){

    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //will be provided at run time via constructor
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Caching(evict = {@CacheEvict(value = "allusercache",allEntries = true),
                      @CacheEvict(value = "usercache",key = "#user.id")
    })
    @Override
    public User saveUser(User user)  {
        return userRepository.save(user);
    }


    @Cacheable(value = "allusercache")//it defines a cache for a methods return value
    @Override
    public List<User> getAllUser() {

        return (List<User>) userRepository.findAll();
    }

    @Cacheable(value = "usercache",key = "#id")
    @Override
    public User getUsrById(int id) {
        User retrivedUser=null;
        retrivedUser=userRepository.findById(id).get();
        return retrivedUser;
    }

    @Caching(evict = {
            @CacheEvict(value = "allusercache",allEntries = true),
            @CacheEvict(value = "usercache",key = "#id")
    })
    @Override
    public User deleteUserById(int id) {
        User user=null;
        Optional optional=userRepository.findById(id);
        if(optional.isPresent()){
            user=userRepository.findById(id).get();
            userRepository.deleteById(id);
        }
        return user;
    }

    @CachePut(key = "#user.id")
    @Override
    public User updateUser(User user) {
        User updatedUser=null;
        Optional optional=userRepository.findById(user.getId());
        if(optional.isPresent()){
            User getUser=userRepository.findById(user.getId()).get();
            getUser.setFirstName(user.getFirstName());
            updatedUser=saveUser(getUser);
        }
        return updatedUser;
    }

}
