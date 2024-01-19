package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.ClassInfo;
import com.app.repo.ClassInfoRepository;
import com.app.service.IClassinfoService;
@Service
public class ClassInfoServiceImpl implements IClassinfoService   {
	@Autowired
	private ClassInfoRepository repo;

	@Override
	public List<ClassInfo> getClassInfo() {
		return repo.findAll();
	}
	
}
