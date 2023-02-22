package com.fundamentosplatzi.springboot.fundamentos.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	private final Log logger = LogFactory.getLog(UserService.class);
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {		
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void saveTransactional(List<User> users) {
		users.stream().peek(user -> logger.info("Usuario insertado: " + user))
		.forEach(userRepository::save);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User save(User newUser) {
		return userRepository.save(newUser);
	}

	public void delete(Long id) {
		userRepository.delete(new User(id));
	}

	public User update(User newUser, Long id) {
		return userRepository.findById(id)
			.map(user -> {
				user.setEmail(newUser.getEmail());
				user.setBirthDate(newUser.getBirthDate());
				user.setName(newUser.getName());
				return userRepository.save(user);
			}).get();
	}
	

}
