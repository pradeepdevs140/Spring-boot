package com.product.user_manage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.user_manage.DTO.ChangePasswordDTO;
import com.product.user_manage.DTO.UserDTO;
import com.product.user_manage.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<UserDTO> getuserById(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
		
	}
	@GetMapping("/getuserbyname/{username}")
	public ResponseEntity<UserDTO> getuserByName(@PathVariable String username){
		return ResponseEntity.ok(userService.getUserByName(username));	
	}
	
	@GetMapping("/getAlluser")
	public ResponseEntity<List<UserDTO> >getAllUser(){
		return ResponseEntity.ok(userService.getAllUser());
	}
	@PutMapping("/changepassword/{id}")
	public ResponseEntity<UserDTO> changePassword(@PathVariable Long id , ChangePasswordDTO changePasswordDTO){
		
		return ResponseEntity.ok(userService.changePassword(id ,changePasswordDTO));
	}
	@PutMapping("/updateUser")
	
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id , @RequestBody UserDTO userDTO)
	{
		return ResponseEntity.ok(userService.updateUser(id , userDTO));
	}
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Void > deleteUser(@PathVariable Long id ){
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	

}
