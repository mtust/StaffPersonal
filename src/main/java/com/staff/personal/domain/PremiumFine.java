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
@Table(name = "PremiumFine")
public class PremiumFine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "premiumFineOrder")
	private String order;

	@Column(name = "deadlines")
	private Date orderDate;

	@Column(name = "serialNumber")
	private String serialNumber; // !!!!!!!!!!!!!!!! STRING?

	@Column(name = "type")
	private String type;

}
