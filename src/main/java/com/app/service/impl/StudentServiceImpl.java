package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Student;
import com.app.exception.SMSException;
import com.app.repo.StudentRepo;
import com.app.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private StudentRepo stdRepo;

	@Override
	public void createStudent(Student std) {
		std.setIsActive(true);
		std.setIsDelete(false);
		stdRepo.save(std);

	}

	@Override
	public void updateStudent(Student std) {
		if (std.getStdId() == null || !stdRepo.existsById(std.getStdId())) {
			throw new SMSException("Student not Exist...");
		} else {
			stdRepo.save(std);
		}

	}

	@Override
	public void deleteStudent(Integer id) {
		stdRepo.delete(getOneStudent(id));

	}

	@Override
	public Student getOneStudent(Integer id) {
		return stdRepo.findById(id).orElseThrow(() -> new SMSException("Student Not Exist!!!"));
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> list = stdRepo.findAll();
		return list;
	}

}
