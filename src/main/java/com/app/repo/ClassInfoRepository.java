package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.ClassInfo;

public interface ClassInfoRepository extends JpaRepository<ClassInfo, Integer> {

}
