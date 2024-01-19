package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "class_info")
public class ClassInfo {
	@Id
	@Column(name = "class_id")
	private Integer classId;
	@Column(name = "class_standard")
	private String classStandard;
	@Column(name = "class_subject")
	private String classSubject;
}
