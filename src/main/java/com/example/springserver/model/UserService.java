package com.example.springserver.model;

import com.example.springserver.file_api.iFileSystemStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private iFileSystemStorage fileSystemStorage;

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
        this.save(userData);
        fileSystemStorage.createDir(userData.getLogin());
        return true;
    }

    public boolean CheckUserPassword(UserData userData){
        if(!CheckUser(userData.getLogin())) return false;
        String temp = userRepository.findPasswordByLogin(userData.getLogin());
        return temp.equals(userData.getPassword());
    }
}
