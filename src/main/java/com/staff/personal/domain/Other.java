package com.staff.personal.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Entity
@Table(name = "other")
public class Other {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "birthplace")
	private String birthplace;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "citizenship")
	private String citizenship;
	
	@Column(name = "seriesAndPassportNumber")
	private String seriesAndPassportNumber;
	
	@Column(name = "seriesAndNumberOfOfficialIdentification")
	private String seriesAndNumberOfOfficialIdentification;
	
	@Column(name = "IdentificationCode")
	private String identificationCode;    //!!!!! STRING?
	
	@Column(name = "placeOfResidence")
	private String placeOfResidence;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	@Column(name = "phoneNumbers")
	private List<PhoneNumber> phoneNumbers;     //!!!!!?
	
	@Column(name = "maritalStatus")
	private String maritalStatus;
	
	@Column(name = "wifeHusband")
	private String wifeHusband;
	
	@Column(name = "children")
	private String children;

	@Column(name = "comment")
	private String comment;


}
