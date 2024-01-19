package com.app.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.Role;
import com.app.entity.User;
import com.app.repo.RoleRepo;
import com.app.repo.UserRepo;
import com.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepo repo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createUser(User user) {
		Optional<Role> role = roleRepo.findByRoleName(user.getRoleName());
		if (role.isPresent()) {
			user.setRoleId(role.get().getRoleId());
			user.setIsActive(true);
			user.setIsDelete(false);
			user.setCeatedOn(LocalDateTime.now());
			user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			repo.save(user);
		} else {
			throw new RuntimeException("Invalid Role type");
		}

	}

}
