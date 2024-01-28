package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Student;
import com.app.response.dto.PageResponseDto;
import com.app.response.dto.ResponseDto;

public interface IStudentService {

	ResponseDto<String> createStudent(Student std);

	ResponseDto<Student> updateStudent(Student std);

	ResponseDto<String> deleteStudent(Integer id);

	ResponseDto<Student> getOneStudent(Integer id);

	ResponseDto<String> imageUpload(MultipartFile file, Integer id) throws IOException;

	PageResponseDto<List<Student>> getAllStudents(Integer pageSize, Integer pageNumber);

//	byte[] downloadImage(String filename);
}
