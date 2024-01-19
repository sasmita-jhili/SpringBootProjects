package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	public Optional<Role> findByRoleName(String roleName);

	Optional<Role> findByRoleId(Integer roleId);

}
