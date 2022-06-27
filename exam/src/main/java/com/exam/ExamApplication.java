package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.exception.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.*;

@SpringBootApplication
public class ExamApplication implements CommandLineRunner {

     @Autowired
     private UserService userService;
     
     @Autowired
     private BCryptPasswordEncoder bCryptPasswordEncorder;

    
	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
		System.out.println("Starting code.....");
		
		User user =new User();
		
		user.setFirstName("srashti");
		user.setLastName("pal");
		user.setUserName("SARUSP");
		user.setPassword(this.bCryptPasswordEncorder.encode("abc"));
		user.setEmail("SP@s.com");
		user.setProfile("default.png");
		
		Role role = new Role();
		role.setRoleid(44L);
		role.setRoleName("ADMIN");
		
		Set<UserRole> userRoleSet= new HashSet<>();
		UserRole userRole =new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoleSet.add(userRole);
		
		User user1 =this.userService.createUser(user,userRoleSet);
		System.out.println(user1.getUsername());
	}catch(UserFoundException e) {
		e.printStackTrace();
	}
	
}
}