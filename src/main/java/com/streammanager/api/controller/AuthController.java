package com.streammanager.api.controller;

import com.streammanager.api.exception.CustomAccessDeniedException;
import com.streammanager.api.exception.CustomUnauthorizedException;
import com.streammanager.api.model.AuthRequest;
import com.streammanager.api.model.AuthResponse;
import com.streammanager.api.model.User;
import com.streammanager.api.response.ResponseJson;
import com.streammanager.api.respository.UserRepository;
import com.streammanager.api.service.AuthService;
import com.streammanager.api.service.JwtService;
import com.streammanager.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private System.Logger logger = System.getLogger(AuthController.class.getName());
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseJson<User> register(@RequestBody AuthRequest authRequest) throws CustomUnauthorizedException {
		logger.log(System.Logger.Level.INFO, "Registering user: " + authRequest.getUsername());
		return new ResponseJson<>(authService.register(authRequest), HttpStatus.OK.value());
	}
	
	@PostMapping("/login")
	public ResponseJson<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws CustomAccessDeniedException {
		logger.log(System.Logger.Level.INFO, "Authenticating user: " + authRequest.getUsername());
		// Trouver l'utilisateur par son login (username ou email)
		User user = userRepository.findByEmailOrUsername(authRequest.getEmail(), authRequest.getUsername());
		if (user == null) {
			throw new CustomAccessDeniedException("User not found");
		}
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), authRequest.getPassword())
		);
		if (authRequest.getRefreshToken() != null) {
			if (Objects.requireNonNull(user).getRefreshToken().equals(authRequest.getRefreshToken())) {
				throw new CustomAccessDeniedException("Refresh token not found");
			}
		}
		user.setRefreshToken(jwtService.createRefreshToken());
		userRepository.save(user);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwtToken(jwtService.generateToken(user.getUsername()));
		authResponse.setRefreshToken(user.getRefreshToken());
		authResponse.setUserId(user.getId());
		authResponse.setUsername(user.getUsername());
		return new ResponseJson<>(authResponse, HttpStatus.OK.value(), jwtService.getExpiration());
	}
	
	@PostMapping("/refresh")
	public ResponseJson<AuthResponse> refreshToken(@RequestBody AuthRequest authRequest) throws CustomUnauthorizedException, CustomAccessDeniedException {
		logger.log(System.Logger.Level.INFO, "Refreshing token for user: " + authRequest.getUsername());
		if (authRequest.getRefreshToken() == null && authRequest.getLogin() == null) {
			throw new CustomUnauthorizedException("Invalid credentials");
		}
		User user = userService.getUserByLogin(authRequest.getLogin());
		if (user == null) {
			throw new CustomAccessDeniedException("User not found");
		}
		if (!user.getRefreshToken().equals(authRequest.getRefreshToken())) {
			throw new CustomAccessDeniedException("Refresh token invalid");
		}
		return new ResponseJson<>(authService.authResponse(user, authRequest.getLogin()), HttpStatus.OK.value(), jwtService.getExpiration());
	}
}
