package com.product.user_manage.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.user_manage.DTO.LoginRequestDTO;
import com.product.user_manage.DTO.LoginResponseDTO;
import com.product.user_manage.DTO.RegisterRequestDTO;
import com.product.user_manage.DTO.UserDTO;
import com.product.user_manage.Entity.User;
import com.product.user_manage.Repository.UserRepository;

@Service
public class AuthenticationService {
	@Autowired
	private AuthenticationManager authenticateManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO registerNormalUser(RegisterRequestDTO registerRequestDTO) {
		
		if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("user is already present");
		}
		Set<String> set = new HashSet<>();
		set.add("ROLE_USER");
		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getEmail()));
		user.setRoles(set);
		User savedUser = userRepository.save(user);
		return convertToUserDTO(savedUser);
	}
	
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		User user = userRepository.findByUsername(loginRequestDTO.getUsername())
				.orElseThrow(()-> new RuntimeException("User Is Not Found"));
		authenticateManager.authenticate(new UsernamePasswordAuthenticationToken
				(loginRequestDTO.getUsername() , loginRequestDTO.getPassword()));
		String jwtToken = jwtService.generateToken(user);
		return LoginResponseDTO.builder()
				.JwtToken(jwtToken)
				.UserDTO(convertToUserDTO(user));
	}
public UserDTO registerAdminUser(RegisterRequestDTO registerRequestDTO) {
		
		if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("user is already present");
		}
		Set<String> set = new HashSet<>();
		set.add("ROLE_ADMIN");
		set.add("ROLE_USER");
		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getEmail()));
		user.setRoles(set);
		User savedUser = userRepository.save(user);
		return convertToUserDTO(savedUser);
	}
	public UserDTO convertToUserDTO(User user)
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setUsername(user.getUsername());
		
		return userDTO;
	}
	

}
