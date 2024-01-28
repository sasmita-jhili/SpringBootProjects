package com.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.constants.ResponseConstants;
import com.app.constants.UserConstant;
import com.app.controller.FacultyRestController;
import com.app.emailservice.EmailService;
import com.app.entity.Faculty;
import com.app.entity.User;
import com.app.exception.SMSException;
import com.app.repo.FacultyRepo;
import com.app.response.dto.PageResponseDto;
import com.app.response.dto.ResponseDto;
import com.app.service.IFacultyService;
import com.app.service.IUserService;
import com.app.util.PasswordGeneratorUtil;

@Service
public class FacultyServiceImpl implements IFacultyService {
	@Autowired
	private FacultyRepo facultyRepo;
	@Autowired
	private IUserService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PasswordGeneratorUtil passwordGeneratorUtil;

	private static final Logger logger = Logger.getLogger(FacultyRestController.class);

	{
		DOMConfigurator.configure("src/main/resources/Log4j.xml");
	}

	@Override
	@Transactional
	public ResponseDto createFaculty(Faculty faculty) {
		try {
			String password = passwordGeneratorUtil.passwordGenerator();
			userService.createUser(User.builder().userName(faculty.getFacultyEmail()).userPassword(password)
					.createdBy("ADMIN").roleName(UserConstant.ROLE_FACULTY).build());
			facultyRepo.save(faculty);

//			// mailservice
			emailService.sendEmail(faculty.getFacultyEmail(), "Default password generate",
					"Your Password is: " + password);

			return ResponseDto.builder().message(ResponseConstants.SUCCESS_FACULTY_CREATE).status(HttpStatus.OK)
					.build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SMSException("Unable to create user");
		}

	}

	@Override
	public ResponseDto<Faculty> updateFaculty(Faculty faculty) {
		try {
			if (faculty.getFacultyId() == null || !facultyRepo.existsById(faculty.getFacultyId())) {
				throw new SMSException("Faculty not Exist...");
			} else {
				Faculty facultyData=facultyRepo.save(faculty);
				return ResponseDto.<Faculty>builder().message(ResponseConstants.SUCCESS_FACULTY_UPDATE)
						.status(HttpStatus.OK)
						.data(facultyData).build();
			}
		} catch (Exception e) {
			throw new SMSException("Unable to update");
		}

	}

	@Override
	public ResponseDto deleteFaculty(Integer id) {
		try {
			facultyRepo.deleteById(id);
			return ResponseDto.builder().message(ResponseConstants.SUCCESS_FACULTY_DELETE)
					.status(HttpStatus.OK).build();
		} catch (Exception e) {
			throw new SMSException("Unable to delete");
		}

	}

	@Override
	public ResponseDto<Faculty> getOneFaculty(Integer id) {
		try {
			Faculty faculty = facultyRepo.findById(id).get();
			return ResponseDto.<Faculty>builder().message(ResponseConstants.SUCCESS_FACULTY_FETCH).status(HttpStatus.OK)
					.data(faculty).build();

		} catch (Exception e) {
			throw new SMSException("Faculty not exist");
		}
	}

	@Override
	public PageResponseDto<List<Faculty>> getAllFaculties(Integer pageSize, Integer pageNumber) {
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("facultyName"));
			Page<Faculty> page = facultyRepo.findAll(pageable);
			List<Faculty> pageList = page.getContent();
			long totalElements = page.getTotalElements();
			return PageResponseDto.<List<Faculty>>builder().message(ResponseConstants.SUCCESS_FACULTY_FETCH)
					.status(HttpStatus.OK).data(pageList).totalCount(totalElements).build();
		} catch (Exception e) {
			throw new SMSException("Unable to fetch faculty list");
		}
	}
}
