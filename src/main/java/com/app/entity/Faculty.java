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
@Table(name="faculty_tbl")
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fcty_id")
	private Integer fctyId;
	@Column(name="fcty_name")
	private String fctyName;
	@Column(name="fcty_email")
	private String fctyEmail;
	@Column(name="fcty_gender")
	private String fctyGender ;
	@Column(name="fcty_address")
	private String fctyAddress;
	@Column(name="fcty_contact")
	private Integer fctyContact;
	@Column(name="fcty_skill")
	private String fctySkills;

}

