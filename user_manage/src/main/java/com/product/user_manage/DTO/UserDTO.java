package com.product.user_manage.DTO;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class UserDTO {
	public UserDTO(Long id, @NotBlank @Size(min = 3, max = 50) String username,
			@NotBlank @Size(min = 70) @jakarta.validation.constraints.Email String email) {
		super();
		this.id = id;
		Username = username;
		Email = email;
	}
	public UserDTO() {
		
	}

	private Long id;
	@NotBlank
	@Size(min =3, max=50)
	private String Username;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@NotBlank
	@Size(min = 70 )
	@Email
	private String Email;

}
