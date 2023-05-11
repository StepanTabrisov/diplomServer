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
        return userService.findUser(login.trim().replaceAll("\"",""));
    }

    @PostMapping("/users/add")
    public UserData AddUser(@RequestBody UserData userData){
        return userService.save(userData);
    }

    // Registration
    @PostMapping("/users/check_user")
    public boolean CheckRegisterUser(@RequestBody UserData userData){
        return userService.CheckUserAndSave(userData);
    }

    // Auth
    @PostMapping("/users/check_user_password")
    public boolean CheckAuthUser(@RequestBody UserData userData){
        return userService.CheckUserPassword(userData);
    }

    /*@PostMapping("/users/find")
    public UserData GetUserByLogin(@RequestBody String login){
        return userService.findUser(login);
    }*/
}
