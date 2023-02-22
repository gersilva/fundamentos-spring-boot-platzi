package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

@Component
public class UpdateUser {
	
	private UserService userService;

	public UpdateUser(UserService userService) {
		this.userService = userService;
	}

	public User update(User user, Long id) {
		return userService.update(user, id);
	}
}
