package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Student;
import com.app.exception.SMSException;
import com.app.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {
	@Autowired
	private StudentServiceImpl service;

	@PostMapping("/create")
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		service.createStudent(student);
		String msg = "Student Details Created";
		return ResponseEntity.ok(msg);

	}

	@GetMapping("/getall")
	public ResponseEntity<List<Student>> studentList() {
		List<Student> list = service.getAllStudents();
		return ResponseEntity.ok(list);

	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Student> getOneStudent(@PathVariable("id") Integer id) {
		ResponseEntity<Student> response = null;
		try {
			Student student = service.getOneStudent(id);
			response = ResponseEntity.ok(student);
		} catch (SMSException e) {
			e.printStackTrace();
			throw new SMSException("Student id not found!!");
		}
		return response;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
		ResponseEntity<String> response = null;
		try {
			service.deleteStudent(id);
			response = ResponseEntity.ok("Student '" + id + "' REMOVED");

		} catch (SMSException e) {
			e.printStackTrace();
			throw new SMSException("Student id not found!!");
		}
		return response;

	}

	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		ResponseEntity<String> response = null;
		try {
			service.updateStudent(student);
			response = ResponseEntity.ok("Student  updated...");
		} catch (SMSException e) {
			e.printStackTrace();
			throw new SMSException("Student not found!!");
		}
		return response;
	}
}
