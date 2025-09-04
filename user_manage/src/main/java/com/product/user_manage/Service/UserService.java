package com.product.user_manage.Service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.user_manage.DTO.ChangePasswordDTO;
import com.product.user_manage.DTO.UserDTO;
import com.product.user_manage.Entity.User;
import com.product.user_manage.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public UserDTO getUserById(Long id) {
		User user =  userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
		return convertToUserDTO(user);
	}
	
	public UserDTO getUserByName(String name) {
		User user =  userRepository.findByUsername(name).orElseThrow(()-> new RuntimeException("user not found"));
		return convertToUserDTO(user);
	}
	public  List<UserDTO> getAllUser(){
		List<User> listofUser = userRepository.findAll();
		return listofUser.stream().map(this::convertToUserDTO).collect(Collectors.toList());
	}
	public UserDTO changePassword(Long id , ChangePasswordDTO  changePasswordDTO ) {
		User user = userRepository.findById(id)
				.orElseThrow(()->new RuntimeException("user not found"));
		if(!passwordEncoder.matches(user.getPassword(), changePasswordDTO.getCurrentPassword() )) {
			throw new RuntimeException("current password is incorrect");
			
		}
		if(!changePasswordDTO.getCurrentPassword().equals(changePasswordDTO.getConfirmPassword())) {
		 throw new RuntimeException("password is matches");
		}
		user.setPassword(passwordEncoder.encode(changePasswordDTO.getCurrentPassword()));
		User savedUser = userRepository.save(user);
	
		return convertToUserDTO(savedUser);
	}
	public UserDTO updateUser(Long id , UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		User saveduser = userRepository.save(user);
		return convertToUserDTO(saveduser);
	}
	public void deleteUser(Long id ) {
		
		userRepository.deleteById(id);
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
