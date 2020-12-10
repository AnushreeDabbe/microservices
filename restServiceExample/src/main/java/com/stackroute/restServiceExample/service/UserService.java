package com.stackroute.restServiceExample.service;

import com.stackroute.restServiceExample.model.User;

import java.util.List;

public interface UserService { public User saveUser(User user) ;
   public List<User> getAllUser();

   public User getUsrById(int id);

   public User deleteUserById(int id);

   public User updateUser(User user);

}
