package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.constants.UserConstant;
import com.app.entity.Faculty;
import com.app.entity.User;
import com.app.exception.SMSException;
import com.app.repo.FacultyRepo;
import com.app.response.dto.PageResponseDto;
import com.app.service.IFacultyService;
import com.app.service.IUserService;

@Service
public class FacultyServiceImpl implements IFacultyService {
	@Autowired
	private FacultyRepo fcrepo;
	@Autowired
	private IUserService userService;

	@Override
	public void createFaculty(Faculty fc) {
		userService.createUser(User.builder()
				.userName(fc.getFctyEmail())
				.userPassword("123")
				.createdBy("SYSTEM")
				.roleName(UserConstant.ROLE_FACULTY).build()
				);
		fcrepo.save(fc);
	}

	@Override
	public void updateFaculty(Faculty fc) {
		if (fc.getFctyId() == null || !fcrepo.existsById(fc.getFctyId())) {
			throw new SMSException("Faculty not Exist...");
		} else {
			fcrepo.save(fc);
		}

	}

	@Override
	public void deleteFaculty(Integer id) {
		fcrepo.delete(getOneFaculty(id));

	}

	@Override
	public Faculty getOneFaculty(Integer id) {
		return fcrepo.findById(id).orElseThrow(() -> new SMSException("Faculty Not Exist!!!"));
	}

	@Override
	public PageResponseDto<List<Faculty>> getAllFaculties(Integer pageSize, Integer pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("fctyName"));
		Page<Faculty> page = fcrepo.findAll(pageable);
		List<Faculty> pageList = page.getContent();
		long totalElements = page.getTotalElements();
		return PageResponseDto.<List<Faculty>>builder()
				.message("Faculty Successfully fetched")
				.status(HttpStatus.OK)
				.data(pageList)
				.totalCount(totalElements).build();
	};
}
