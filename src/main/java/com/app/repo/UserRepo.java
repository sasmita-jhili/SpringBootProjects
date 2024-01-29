package com.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String username);

	@Query("SELECT userName FROM User")
	List<String> findAllEmail();
}
