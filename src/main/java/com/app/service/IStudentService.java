package com.app.service;

import java.util.List;

import com.app.entity.Student;

public interface IStudentService {

	void createStudent(Student std);

	void updateStudent(Student std);

	void deleteStudent(Integer id);

	Student getOneStudent(Integer id);

	List<Student> getAllStudents();
}
