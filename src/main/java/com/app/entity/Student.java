package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_tbl")
@Builder
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "std_id")
	private Integer stdId;
	@Column(name = "std_name")
	private String stdName;
	@Column(name = "std_email")
	private String stdEmail;
	@Column(name = "std_class")
	private String stdClass;
	@Column(name = "std_rollno")
	private Integer stdRollNo;
	@Column(name = "std_address")
	private String stdAddress;
	@Lob
	@Column(name = "std_image", length = 1000)
	private byte[] stdImage;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "is_delete")
	private Boolean isDelete;
}
