package com.example.springserver.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserData, Integer> {

    @Query(value = "SELECT * FROM User_Data WHERE USER_LOGIN = ?1", nativeQuery=true)
    UserData findUserByLogin(String login);

    @Query(value = "SELECT USER_PASSWORD FROM User_Data WHERE USER_LOGIN = ?1", nativeQuery=true)
    String findPasswordByLogin(String login);
}
