package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Faculty;

public interface FacultyRepo extends JpaRepository<Faculty, Integer> {

}
