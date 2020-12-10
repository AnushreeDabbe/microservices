package com.stackroute.restServiceExample.controller;

import com.stackroute.restServiceExample.model.User;
import com.stackroute.restServiceExample.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")  //represtents complete http response including body,status code and header
    public ResponseEntity<User> saveUser(@RequestBody User user)  {
        User savedUser=userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        logger.info(".....Fetching all users");
        ResponseEntity responseEntity;
        responseEntity=new ResponseEntity<List<User>>((List<User>)userService.getAllUser(), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("user/{id}")
        public ResponseEntity<User> deleteUser(@PathVariable("id") int id)  {

        ResponseEntity responseEntity;
        User deleteUser =userService.deleteUserById(id);
        responseEntity=new ResponseEntity<User>(deleteUser,HttpStatus.OK);
        return responseEntity;

        }

        @PutMapping("user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        logger.info("........Updating User contact of id : "+user.getId());
        User updatedUser=userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }

}
