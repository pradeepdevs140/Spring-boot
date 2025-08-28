package com.product.user_manage.DTO;

import lombok.Builder;

@Builder
public class LoginResponseDTO {
	private String JwtToken;
	private UserDTO userDTO;
	public String getJwtToken() {
		return JwtToken;
	}
	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public LoginResponseDTO(String jwtToken, UserDTO userDTO) {
		super();
		JwtToken = jwtToken;
		this.userDTO = userDTO;
	}
	public LoginResponseDTO() {
		
	}


}
