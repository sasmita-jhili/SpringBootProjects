package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.ClassInfo;
import com.app.service.IClassinfoService;

@RestController
@RequestMapping("/api/classinfo")
public class ClassInfoController {
	@Autowired
	private IClassinfoService service;

	@GetMapping("/get")
	public ResponseEntity<List<ClassInfo>> getclassinfo() {
		return ResponseEntity.ok(service.getClassInfo());

	}
}
