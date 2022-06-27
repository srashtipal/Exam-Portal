package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exception.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stub
		
		
		User local = this.userRepository.findByuserName(user.getUserName());
		if(local!=null)
		{
			System.out.println("User is already there !!");
			throw new UserFoundException();
		}else
		{
			//user create
		  for(UserRole ur:userRoles)
		  {
			  roleRepository.save(ur.getRole());
		  }
		  user.getUserRole().addAll(userRoles);
		  local =this.userRepository.save(user);
		}
		return local;
	}
	
    //getting user by username
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByuserName(username);
	}

	//delete by Id
	@Override
	public void deleteUser(Long Id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(Id);
		
	}
    //updating user
	@Override
	public User updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		User userData = this.userRepository.findByuserName(user.getUserName());
		
		System.out.println(userData.getUserName());
		User newUserData = null;
		if(userData == null) {
			System.out.println("User already present");
			throw new UserFoundException();
		   }else {
			   //user create
			userData.setFirstName(user.getFirstName());
			userData.setEmail(user.getEmail());
			userData.setLastName(user.getLastName());
			userData.setUserName(user.getUserName());
			userData.setPassword(user.getPassword());
		    newUserData = this.userRepository.save(userData);
		}
		return newUserData;
	}
	
	}

