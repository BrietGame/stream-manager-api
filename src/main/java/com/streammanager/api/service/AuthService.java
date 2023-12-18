package com.streammanager.api.service;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.streammanager.api.exception.CustomUnauthorizedException;
import com.streammanager.api.model.AuthRequest;
import com.streammanager.api.model.AuthResponse;
import com.streammanager.api.model.User;
import com.streammanager.api.model.dto.UserDTO;
import com.streammanager.api.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
	UserRepository userRepository;
	
	@Autowired
	private Environment environment;
	
	UserService userService;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.userService = new UserService(userRepository);
	}
	
	public User register(AuthRequest authRequest) throws CustomUnauthorizedException {
		if (userRepository.existsByUsername(authRequest.getUsername())) {
			throw new CustomUnauthorizedException("Username already exists");
		} else if (userRepository.existsByEmail(authRequest.getEmail())) {
			throw new CustomUnauthorizedException("Email already exists");
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(authRequest.getUsername());
		userDTO.setEmail(authRequest.getEmail());
		userDTO.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
		
		return userService.create(userDTO);
	}
	
	public AuthResponse authResponse(User user, String username) {
		user.setRefreshToken(jwtService.createRefreshToken());
		userRepository.save(user);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwtToken(jwtService.generateToken(username));
		authResponse.setRefreshToken(user.getRefreshToken());
		authResponse.setUserId(user.getId());
		authResponse.setUsername(user.getUsername());
		return authResponse;
	}
}
