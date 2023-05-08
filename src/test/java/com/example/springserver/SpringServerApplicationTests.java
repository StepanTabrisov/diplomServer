package com.example.springserver;

import com.example.springserver.model.UserController;
import com.example.springserver.model.UserData;
import com.example.springserver.model.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringServerApplicationTests {

	@Autowired
	private UserService userService;

	private UserController userController;
	//@Test
	void addUser() {
		UserData userData = new UserData();
		userData.setName("aaa2");
		userData.setLogin("bbb2");
		userData.setEmail("ccc2");
		userData.setPassword("ddd2");

		userService.save(userData);
	}

	@Test
	void getUser(){
		System.out.println(userService.findUser("bbb2"));
	}

	//@Test
	void getUsers(){
		System.out.println(userService.getAllUsers());
	}
}
