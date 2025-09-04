package com.product.user_manage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.user_manage.DTO.RegisterRequestDTO;
import com.product.user_manage.DTO.UserDTO;
import com.product.user_manage.Service.AuthenticationService;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	
	@Autowired
	private AuthenticationService authenticationService;
	@PostMapping("/registeradminuser")
	public ResponseEntity<UserDTO> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO){

			return ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDTO));
	}

}
