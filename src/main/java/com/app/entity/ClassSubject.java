package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassSubject {
	@Id
	@Column(name = "cls_sub_id")
	private Integer clsSubId;
	@Column(name = "cls_sub_one")
	private String clsSubOne;
	@Column(name = "cls_sub_two")
	private String clsSubTwo;
	@Column(name = "cls_sub_three")
	private String clsSubThree;
	@Column(name = "cls_sub_four")
	private String clsSubFour;
	@Column(name = "cls_sub_five")
	private String clsSubFive;
}
