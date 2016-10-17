package com.staff.personal.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "education")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	MainEducationBlock block;

	@Column(name = "educationalLevel")
	String educationalLevel;// Освітний рівень??

	@Column(name = "school")
	String school;

	@Column(name = "educationLevel")
	private String educationLevel;// Освітньо-кваліфікаційний рівень??

	@Column(name = "qualification")
	private String qualification;

	@Column(name = "specialty")
	private String specialty;

	@Column(name = "SeriesAndNumberOfDiploma")
	private String SeriesAndNumberOfDiploma;

}
