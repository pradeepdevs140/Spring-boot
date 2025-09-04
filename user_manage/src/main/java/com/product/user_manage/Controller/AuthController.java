package com.product.user_manage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.user_manage.DTO.LoginRequestDTO;
import com.product.user_manage.DTO.LoginResponseDTO;
import com.product.user_manage.DTO.RegisterRequestDTO;
import com.product.user_manage.DTO.UserDTO;
import com.product.user_manage.Entity.User;
import com.product.user_manage.Repository.UserRepository;
import com.product.user_manage.Service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	public AuthenticationService authenticationService;
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){

			return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
	}
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
		LoginResponseDTO loginResponseDTO =  authenticationService.login(loginRequestDTO);
		ResponseCookie Cookie = ResponseCookie.from("JWT" ,loginResponseDTO.getJwtToken() )
				.httpOnly(true)
				.secure(true)
				.path("/")
				.maxAge(1*60*60)
				.sameSite("Strict")
				.build();
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, Cookie.toString())
				.body(loginResponseDTO.getUserDTO());
	}
	@PostMapping("/logout")
	public ResponseEntity<String> logout(){
		return authenticationService.logout();
	}
	@GetMapping("/getcurrentuser")
	public ResponseEntity<?> getCurrebtUser(Authentication authentication){
		if(authentication ==null)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user is not Authenticated ");
		}
		String name = authentication.getName();
		User user = userRepository.findByUsername(name)
				.orElseThrow(()-> new RuntimeException("user not found") );
		return ResponseEntity.ok(convertToUserDTO(user));
		
	
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