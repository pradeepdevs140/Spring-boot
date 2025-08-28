package com.product.user_manage.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.user_manage.Entity.User;

public interface UserRepository extends JpaRepository<User , Long> {

	Optional<User> findByUsername(String name);

	Optional<User> findByEmail(String name);

	
}
