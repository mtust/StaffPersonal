package com.staff.personal.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "MainStaff")
public class MainStaff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "fullName")//ПІБ
	private String fullName;
	
	@Column(name = "specRank")
	private String specialRank;
	
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;

	@Column(name = "position")
	private String position;

	/**
	 *номер дата присвоєння спеціальних звань
	 */
	@Column(name = "numberConferringSpeclRanks")
	private String numberConferringSpeclRanks;

	/**
	 *номер дата присвоєння спеціальних звань
	 */
	@Column(name = "dateConferringSpecRanks")
	private Date dateConferringSpecRanks;

	/**
	 *Дата призначення
	 */
	@Column(name = "dateNumberPurpose")
	private Date dateNumberPurpose;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	/**
	 *Дати контракту: «з»
	 */
	@Column(name = "contractFromDate")
	private Date contractFromDate;
	/**
	 *Дати контракту: «по»
	 */
	@Column(name = "contractToDate")
	private Date contractToDate;
	
	/*
	 *Звільнення зі служби цивільного захисту, дата та номер наказу
	 */
	@Column(name = "exemptionDate")
	private Date exemptionDate;
	
	/*
	 *Звільнення зі служби цивільного захисту, дата та номер наказу
	 */
	@Column(name = "exemptionNumOrder")
	private String exemptionNumOrder ;
	/**
	 *У розпорядженні відовідального керівника, дата та номер наказу
	 */
	@Column(name = "inCommand")
	private String inCommand;
	/**
	 *Дата прийняття присяги державного службовця
	 */
	@Column(name = "dateSwear")
	private Date dateSwear;
	
	@Column(name = "rankCivilServant")
	private String rankCivilServant;
	/*
	 *Категорія державного службовця
	 */
	@Column(name = "categoriesCivilServants")
	private String categoriesCivilServants;
	/*
	 *Група оплати праці
	 */
	@Column(name = "groupRemuneration")
	private String groupRemuneration;
	
	/*
	 *Штатно-посадова категорія
	 */
	@Column(name = "staffOfficerCategory")
	private String staffOfficerCategory;
	
	@Column(name = "lastCertification")
	private Date lastCertification;
	
	/*
	 *Висновок атестації
	 */
	@Column(name = "concludedCertification")
	private String concludedCertification;
	/*
	 *Кадровий резерв на посаду
	 */
	@Column(name = "personnelProvisionForPost")
	private String personnelProvisionForPost;
	
	
	@Column(name = "biography")
	private String biography;
	
	@Column(name = "photoMainStaffs")
	@OneToMany
	private List<MainStaffPhotos> photoMainStaffs;
	
	@Column(name = "documents")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<StuffDocuments> documents;



}
