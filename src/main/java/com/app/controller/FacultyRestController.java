package com.app.controller;

import java.util.List;

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

import com.app.emailservice.EmailService;
import com.app.entity.Faculty;
import com.app.exception.SMSException;
import com.app.response.dto.PageResponseDto;
import com.app.service.IFacultyService;
import com.app.service.impl.FacultyServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/faculty")
@SecurityRequirement(name = "Bearer Authentication")
public class FacultyRestController {
	@Autowired
	private IFacultyService service;
//	@Autowired
//	private EmailService emailService;

	@PostMapping("/create")
	public ResponseEntity<String> addFaculty(@RequestBody Faculty fc) {
		service.createFaculty(fc);
		String msg = "Faculty Details Created";
		return ResponseEntity.ok(msg);
//		String to = fc.getFctyEmail();
//        String subject = "Registration Confirmation";
//        String body = "Thank you for registering. Welcome to our platform!";
//        emailService.sendRegistrationEmail(to, subject, body);

	}

	@GetMapping("/getall/{pageNumber}/{pageSize}")
	public ResponseEntity<PageResponseDto<List<Faculty>>> facultyList(@PathVariable Integer pageSize,
			@PathVariable Integer pageNumber) {
		PageResponseDto<List<Faculty>> response = service.getAllFaculties(pageSize, pageNumber);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Faculty> getOneFaculty(@PathVariable Integer id) {
		ResponseEntity<Faculty> response = null;
		try {
			Faculty faculty = service.getOneFaculty(id);
			response = ResponseEntity.ok(faculty);
		} catch (SMSException e) {
			e.printStackTrace();
			throw new SMSException("Faculty id not found!!");
		}
		return response;
	}

	@DeleteMapping("/remove/{id}")

	public ResponseEntity<String> deleteFaculty(@PathVariable("id") Integer id) {
		ResponseEntity<String> response = null;
		try {
			service.deleteFaculty(id);
			response = ResponseEntity.ok("Faculty '" + id + "' REMOVED");

		} catch (SMSException e) {
			e.printStackTrace();
			throw new SMSException("Faculty id not found!!");
		}
		return response;

	}

	@PutMapping("/update")
	public ResponseEntity<String> updateFaculty(@RequestBody Faculty faculty) {
		ResponseEntity<String> response = null;
		try {
			service.updateFaculty(faculty);
			response = ResponseEntity.ok("Faculty  updated...");
		} catch (SMSException e) {
			e.printStackTrace();
			throw new SMSException("Faculty not found!!");
		}
		return response;
	}
}
