package com.app.service;

import java.util.List;

import com.app.entity.Faculty;
import com.app.response.dto.PageResponseDto;
import com.app.response.dto.ResponseDto;

public interface IFacultyService {

	ResponseDto<String> createFaculty(Faculty fc);

	ResponseDto<Faculty> updateFaculty(Faculty fc);

	ResponseDto<Faculty> deleteFaculty(Integer id);

	ResponseDto<Faculty> getOneFaculty(Integer id);

	PageResponseDto<List<Faculty>> getAllFaculties(Integer pageSize, Integer pageNumber);
	
}
