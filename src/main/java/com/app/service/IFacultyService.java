package com.app.service;

import java.util.List;

import com.app.entity.Faculty;
import com.app.response.dto.PageResponseDto;

public interface IFacultyService {

	void createFaculty(Faculty fc);

	void updateFaculty(Faculty fc);

	void deleteFaculty(Integer id);

	Faculty getOneFaculty(Integer id);

	PageResponseDto<List<Faculty>> getAllFaculties(Integer pageSize, Integer pageNumber);
}
