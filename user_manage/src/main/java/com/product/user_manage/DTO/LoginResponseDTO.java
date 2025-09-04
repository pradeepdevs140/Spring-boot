package com.product.user_manage.DTO;

public class LoginResponseDTO {
    private String jwtToken;
    private UserDTO userDTO;

    // --- Constructors ---
    public LoginResponseDTO() {}

    public LoginResponseDTO(String jwtToken, UserDTO userDTO) {
        this.jwtToken = jwtToken;
        this.userDTO = userDTO;
    }

    // --- Getters & Setters ---
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    // --- Manual Builder ---
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String jwtToken;
        private UserDTO userDTO;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder userDTO(UserDTO userDTO) {
            this.userDTO = userDTO;
            return this;
        }

        public LoginResponseDTO build() {
            return new LoginResponseDTO(jwtToken, userDTO);
        }
    }
}
