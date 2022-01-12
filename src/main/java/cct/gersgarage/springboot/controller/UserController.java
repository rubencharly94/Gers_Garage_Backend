package cct.gersgarage.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cct.gersgarage.springboot.model.User;
import cct.gersgarage.springboot.repository.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	

	@GetMapping("/userlist")
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
}
}
