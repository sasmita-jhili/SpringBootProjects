package com.app.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.constants.ResponseConstants;
import com.app.constants.UserConstant;
import com.app.entity.Student;
import com.app.entity.User;
import com.app.exception.SMSException;
import com.app.repo.StudentRepo;
import com.app.response.dto.PageResponseDto;
import com.app.response.dto.ResponseDto;
import com.app.service.IStudentService;
import com.app.service.IUserService;
import com.app.util.ImageUtil;
import com.app.util.PasswordGeneratorUtil;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private StudentRepo stdRepo;
	@Autowired
	private IUserService userService;
	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private PasswordGeneratorUtil passwordGeneratorUtil;

	@Transactional
	@Override
	public ResponseDto<String> createStudent(Student student) {
		try {
			student.setIsActive(true);
			student.setIsDelete(false);
			String password = passwordGeneratorUtil.passwordGenerator();
			userService.createUser(User.builder().userName(student.getStdEmail()).userPassword(password)
					.createdBy("ADMIN").roleName(UserConstant.ROLE_STUDENT).build());
			stdRepo.save(student);
			return ResponseDto.<String>builder().message(ResponseConstants.SUCCESS_STUDENT_CREATE).status(HttpStatus.OK)
					.build();
		} catch (Exception e) {
			throw new SMSException(ResponseConstants.UNABLE_TO_CREATE_STUDENT);
		}
	}

	@Override
	public ResponseDto<Student> updateStudent(Student student) {
		try {
			if (student.getStdId() == null || !stdRepo.existsById(student.getStdId())) {
				throw new SMSException("Student not Exist...");
			} else {
				Student studentData = stdRepo.save(student);
				return ResponseDto.<Student>builder().message(ResponseConstants.SUCCESS_STUDENT_UPDATE)
						.status(HttpStatus.OK).data(studentData).build();
			}
		} catch (Exception e) {
			throw new SMSException(ResponseConstants.UNABLE_TO_UPDATE_STUDENT);
		}

	}

	@Override
	public ResponseDto<String> deleteStudent(Integer id) {
		try {
			stdRepo.deleteById(id);
			return ResponseDto.<String>builder().message(ResponseConstants.SUCCESS_STUDENT_DELETE).status(HttpStatus.OK)
					.build();
		} catch (Exception e) {
			throw new SMSException(ResponseConstants.STUDENT_ID_NOT_EXIST);
		}
	}

	@Override
	public ResponseDto<Student> getOneStudent(Integer id) {
		try {
			Student student = stdRepo.findById(id).get();
			return ResponseDto.<Student>builder().message(ResponseConstants.SUCCESS_STUDENT_FETCH).status(HttpStatus.OK)
					.data(student).build();
		} catch (Exception e) {
			throw new SMSException(ResponseConstants.STUDENT_ID_NOT_FOUND);
		}
	}

	@Override
	public PageResponseDto<List<Student>> getAllStudents(Integer pageSize, Integer pageNumber) {
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("stdName"));
			Page<Student> page = stdRepo.findAll(pageable);
			List<Student> pageList = page.getContent();
			long totalElements = page.getTotalElements();
			return PageResponseDto.<List<Student>>builder().message(ResponseConstants.SUCCESS_STUDENT_FETCH)
					.status(HttpStatus.OK).data(pageList).totalCount(totalElements).build();
		} catch (Exception e) {
			throw new SMSException(ResponseConstants.STUDENT_LIST_NOT_FOUND);
		}
	}

	@Transactional
	@Override
	public ResponseDto<String> imageUpload(MultipartFile file, Integer id) throws IOException {
		try {
			if (imageUtil.isValidImage(file)) {
				stdRepo.imageUpdate(id, imageUtil.compressImage(file.getBytes()));
				return ResponseDto.<String>builder().message(ResponseConstants.SUCCESSFULLY_IMAGE_UPDATE)
						.status(HttpStatus.OK).build();
			} else {
				throw new SMSException(ResponseConstants.INVALID_FILE_FOUND);
			}
		} catch (Exception e) {
			throw new SMSException(ResponseConstants.UNABLE_TO_UPLOAD_IMAGE);
		}

	}

}
