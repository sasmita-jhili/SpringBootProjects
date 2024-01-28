package com.app.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.app.entity.Role;
import com.app.entity.User;
import com.app.repo.RoleRepo;
import com.app.repo.UserRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(username);
		if (user.isPresent()) {
			Optional<Role> role = roleRepo.findByRoleId(user.get().getRoleId());
			user.get().setRoleName(role.get().getRoleName());
		} else {
			throw new UsernameNotFoundException("User not found : " + username);
		}

		return user.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));

	}

}
