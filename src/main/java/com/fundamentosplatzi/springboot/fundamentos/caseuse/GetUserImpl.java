package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import java.util.List;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

public class GetUserImpl implements GetUser{

	private UserService userService;
	
	public GetUserImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userService.getAllUsers();
	}

}
