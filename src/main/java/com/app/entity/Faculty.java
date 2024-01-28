package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "faculty_tbl")
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fcty_id")
	private Integer facultyId;
	@Column(name = "fcty_name")
	private String facultyName;
	@Column(name = "fcty_email")
	private String facultyEmail;
	@Column(name = "fcty_gender")
	private String facultyGender;
	@Column(name = "fcty_address")
	private String facultyAddress;
	@Column(name = "fcty_contact")
	private Integer facultyContact;
	@Column(name = "fcty_skill")
	private String facultySkills;

}
