package com.example.springserver.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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
}
