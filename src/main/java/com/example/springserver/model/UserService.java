package com.example.springserver.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserData save(UserData userData){
       return userRepository.save(userData);
    }

    public void delete(UserData userData){
        userRepository.delete(userData);
    }

    public List<UserData> getAllUsers(){
        List<UserData> result = new ArrayList<>();
        Streamable.of(userRepository.findAll()).forEach(result::add);
        return result;
    }

    public UserData findUser(String login){
        return userRepository.findUserByLogin(login);
    }

    public boolean CheckUser(String login){
        UserData userData = userRepository.findUserByLogin(login);
        return userData != null;
    }

    public boolean CheckUserAndSave(UserData userData){
        UserData temp = userRepository.findUserByLogin(userData.getLogin());
        if(temp != null) return false;
        if(temp.getLogin().equals(userData.getLogin())) return false;
        this.save(userData);
        return true;
    }

    public boolean CheckUserPassword(UserData userData){
        if(!CheckUser(userData.getLogin())) return false;
        String temp = userRepository.findPasswordByLogin(userData.getLogin());
        return temp.equals(userData.getPassword());
    }
}
