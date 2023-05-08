package com.example.springserver.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/get-all")
    public List<UserData> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users/find-user")
    public UserData GetUserByLogin(@RequestBody String login){
        //System.out.println(userService.findUser(login) + " " + login.trim().replace());
        return userService.findUser(login.trim().replaceAll("\"",""));
    }

    @PostMapping("/users/add")
    public UserData AddUser(@RequestBody UserData userData){
        return userService.save(userData);
    }

    /*@PostMapping("/users/find")
    public UserData GetUserByLogin(@RequestBody String login){
        return userService.findUser(login);
    }*/
}
