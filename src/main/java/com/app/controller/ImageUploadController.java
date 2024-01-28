//package com.app.controller;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.app.service.IImageUpload;
//
//@RestController
//@RequestMapping("/api/image")
//public class ImageUploadController {
//	@Autowired
//	private IImageUpload service;
//
//	@PatchMapping("/update/{id}")
//	public ResponseEntity<String> uploadImage(@PathVariable("id") MultipartFile file) throws IOException {
//		
//try {
//	service.imageUpload(file, null)
//} catch (Exception e) {
//	// TODO: handle exception
//}
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//	}
//}
