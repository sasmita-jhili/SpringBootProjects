package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

	@Modifying
	@Query("UPDATE Student SET stdImage =:byteImage WHERE stdId =:id")
	Integer imageUpdate(Integer id, byte[] byteImage);

}
