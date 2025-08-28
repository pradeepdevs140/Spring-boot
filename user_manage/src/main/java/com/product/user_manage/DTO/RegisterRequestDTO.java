package com.product.user_manage.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {

	public RegisterRequestDTO(@NotBlank String username, @NotBlank @Email String email,
			@NotBlank @Size(min = 8, max = 15) String password) {
		super();
		Username = username;
		this.email = email;
		this.password = password;
	}
	public RegisterRequestDTO() {
		
	}
	@NotBlank
	private String Username;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@NotBlank
	@Email
	private String email ;
	@NotBlank
	@Size(min = 8 , max = 15)
	private String password;
	
	
}
