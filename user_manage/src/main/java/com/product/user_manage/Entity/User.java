package com.product.user_manage.Entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name =  "users")


public class User {
	 public User(long id, String username, String password, String email, Boolean isactive, LocalDateTime createdAt,
			LocalDateTime updatedAt, Set<String> roles) {
		super();
		this.id = id;
		Username = username;
		Password = password;
		this.email = email;
		this.isactive = isactive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = roles;
	}
	 public  User() {
		 
	 }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	private String Username ;
	private String Password;
	private String email;
	private Boolean isactive = true;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private  Set<String> roles;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	

}
