package com.fundamentosplatzi.springboot.fundamentos.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	//create, get, delete, update
	
	private GetUser getUser;
	private CreateUser createUser;
	private DeleteUser deleteUser;
	private UpdateUser updateUser;
	private UserService userService;
	private UserRepository userRepository;
	
	public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser, UserService userService, UserRepository userRepository) {
		this.getUser = getUser;
		this.createUser = createUser;
		this.deleteUser = deleteUser;
		this.updateUser = updateUser;
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@GetMapping("/")
	List<User> get(){
		return getUser.getAll();
	}
	
	@PostMapping("/")
	ResponseEntity<User> addUser(@RequestBody User newUser){
		return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
		return new ResponseEntity<User>(updateUser.update(user, id), HttpStatus.OK); 
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteUser(@PathVariable Long id){
		deleteUser.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public List<User> getAlls(@RequestParam int page, @RequestParam int size){
	return userRepository.findAll(PageRequest.of(page, size)).getContent();
	}
	

}
