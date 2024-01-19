package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String username);
}
