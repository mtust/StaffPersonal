package com.staff.personal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "hospitals")
public class Hospitals {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "typeHospital")
    private String typeHospital;
	
	@Column(name = "fromDate")
	private Date fromDate;
	
	@Column(name = "toDate")
	private Date toDate;

	@Column(name = "description")
	private String description;


}
