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
@Table(name = "benefits")
public class Benefits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "fromDate")
	private Date fromDate;

	@Column(name = "toDate")
	private Date toDate;

	@Column(name = "order")
	private String order;

	@Column(name = "oerderDate")
	private Date oerderDate;

	@Column(name = "certification")
	private String certification;

	// пільгова вислуга
	@Column(name = "privilege")
	private String privilege;

	@Column(name = "actsAndComments")
	private String actsAndComments;

	@Column(name = "otherInfo")
	private String otherInfo;

}
