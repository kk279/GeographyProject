package com.geography.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/addUser")
    public User saveUser(@RequestParam Double latitude, @RequestParam Double longitude){

        return userService.addUser(latitude, longitude);
    }

    @GetMapping("/get")
    public List<User> getAllUsers(){

        return userService.getAllUsers();
    }
}
