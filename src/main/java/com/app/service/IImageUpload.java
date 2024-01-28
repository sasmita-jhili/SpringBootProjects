package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Student;

public interface IImageUpload {
	String imageUpload(MultipartFile file, Integer id,Student student) throws IOException;
}
