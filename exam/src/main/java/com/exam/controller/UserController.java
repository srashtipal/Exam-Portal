package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncorder;

	//creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception

	{
		user.setProfile("default.png");
		
		//encoder password with bcrypt 
		user.setPassword(this.bCryptPasswordEncorder.encode(user.getPassword()));
		
		Set<UserRole> roles =new HashSet<>();
		
		Role role =new Role();
		role.setRoleid(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole= new  UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		return this.userService.createUser(user, roles);
	}
	
	//getting data for particular user
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String username)
	{
		return this.userService.getUser(username);
	}
	
	//deleting user by id
	@DeleteMapping("/{Id}")
	public void deleteUser(@PathVariable("Id") Long Id)
	{
		this.userService.deleteUser(Id);
	}
	
	//updating user
	@PutMapping("/")
		public User updateUser(@RequestBody User user) throws Exception 
	{
		return this.userService.updateUser(user);
	}

}
