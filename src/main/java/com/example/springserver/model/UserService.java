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
        //System.out.println(userRepository.findByLogin(login) + " " + login);
       return userRepository.findByLogin(login);
    }
}
