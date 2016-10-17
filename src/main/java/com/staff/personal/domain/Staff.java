package com.staff.personal.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "MainStaff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private MainStaff mainStaff;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Education education;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Other other;

	@OneToMany(fetch = FetchType.LAZY)
	private List<WorkExperience> workExperiences;
	

	@OneToMany(fetch = FetchType.LAZY)
	private List<Benefits> benefits;
	

	@OneToMany(fetch = FetchType.LAZY)
	private List<Promotion> promotions;
	

	@OneToMany(fetch = FetchType.LAZY)
	private List<Hospitals> hospitals;
	

	@OneToMany(fetch = FetchType.LAZY)
	private List<Holiday> holidays;
	

	@OneToMany(fetch = FetchType.LAZY)
	private List<PremiumFine> premiumFines;
	
	//need add lustration, firing
}
