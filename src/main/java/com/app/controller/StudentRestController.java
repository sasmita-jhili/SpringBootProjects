package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Student;
import com.app.response.dto.PageResponseDto;
import com.app.response.dto.ResponseDto;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {
	@Autowired
	private IStudentService service;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto<String>> addStudent(@RequestBody Student std) {
		ResponseDto<String> response = service.createStudent(std);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getall/{pageNumber}/{pageSize}")
	public ResponseEntity<PageResponseDto<List<Student>>> studentList(@PathVariable Integer pageSize,
			@PathVariable Integer pageNumber) {
		PageResponseDto<List<Student>> response = service.getAllStudents(pageSize, pageNumber);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseDto<Student>> getOneStudent(@PathVariable("id") Integer id) {
		ResponseDto<Student> response = service.getOneStudent(id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDto<String>> deleteStudent(@PathVariable("id") Integer id) {
		ResponseDto<String> response = service.deleteStudent(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto<Student>> updateStudent(@RequestBody Student student) {
		ResponseDto<Student> response = service.updateStudent(student);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/image/{id}")
	public ResponseEntity<ResponseDto<String>> uploadImage(@PathVariable("id") Integer id,
			@RequestPart("file") MultipartFile file) throws IOException {
		ResponseDto<String> response = service.imageUpload(file, id);
		return ResponseEntity.ok(response);
	}
}
