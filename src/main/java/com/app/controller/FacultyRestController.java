package com.app.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.constants.ResponseConstants;
import com.app.entity.Faculty;
import com.app.exception.SMSException;
import com.app.response.dto.PageResponseDto;
import com.app.response.dto.ResponseDto;
import com.app.service.IFacultyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/faculty")
@SecurityRequirement(name = "Bearer Authentication")
public class FacultyRestController {
	@Autowired
	private IFacultyService service;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto<String>> addFaculty(@RequestBody Faculty fc) {
		ResponseDto<String> response = service.createFaculty(fc);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getall/{pageNumber}/{pageSize}")
	public ResponseEntity<PageResponseDto<List<Faculty>>> facultyList(@PathVariable Integer pageSize,
			@PathVariable Integer pageNumber) {
		PageResponseDto<List<Faculty>> response = service.getAllFaculties(pageSize, pageNumber);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseDto<Faculty>> getOneFaculty(@PathVariable Integer id) {
		ResponseDto<Faculty> response = service.getOneFaculty(id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDto<Faculty>> deleteFaculty(@PathVariable("id") Integer id) {
		ResponseDto<Faculty> response = service.deleteFaculty(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto<Faculty>> updateFaculty(@RequestBody Faculty faculty) {
		ResponseDto<Faculty> response = service.updateFaculty(faculty);
		return ResponseEntity.ok(response);
	}
}
