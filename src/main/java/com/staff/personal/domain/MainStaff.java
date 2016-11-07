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
	String fullName;
	
	@Column(name = "specRank")
	String specialRank;
	
	@Column(name = "dateOfBirth")
	Date dateOfBirth;

	@Column(name = "position")
	String position;

	/**
	 *номер дата присвоєння спеціальних звань
	 */
	@Column(name = "numberConferringSpeclRanks")
	String numberConferringSpeclRanks;
	
	/**
	 *номер дата присвоєння спеціальних звань
	 */
	@Column(name = "dateConferringSpecRanks")
	Date dateConferringSpecRanks;

	/**
	 *Дата призначення
	 */
	@Column(name = "dateNumberPurpose")
	Date dateNumberPurpose;
	
	@Column(name = "phoneNumber")
	String phoneNumber;
	
	/**
	 *Дати контракту: «з»
	 */
	@Column(name = "contractFromDate")
	Date contractFromDate;
	/**
	 *Дати контракту: «по»
	 */
	@Column(name = "contractToDate")
	Date contractToDate;
	
	/*
	 *Звільнення зі служби цивільного захисту, дата та номер наказу
	 */
	@Column(name = "exemptionDate")
	Date exemptionDate;
	
	/*
	 *Звільнення зі служби цивільного захисту, дата та номер наказу
	 */
	@Column(name = "exemptionNumOrder")
	String exemptionNumOrder ;
	/**
	 *У розпорядженні відовідального керівника, дата та номер наказу
	 */
	@Column(name = "inCommand")
	String inCommand;
	/**
	 *Дата прийняття присяги державного службовця
	 */
	@Column(name = "dateSwear")
	Date dateSwear;
	
	@Column(name = "rankCivilServant")
	String rankCivilServant;
	/*
	 *Категорія державного службовця
	 */
	@Column(name = "categoriesCivilServants")
	String categoriesCivilServants;
	/*
	 *Група оплати праці
	 */
	@Column(name = "groupRemuneration")
	String groupRemuneration;
	
	/*
	 *Штатно-посадова категорія
	 */
	@Column(name = "staffOfficerCategory")
	String staffOfficerCategory;
	
	@Column(name = "lastCertification")
	Date lastCertification;
	
	/*
	 *Висновок атестації
	 */
	@Column(name = "concludedCertification")
	String concludedCertification;
	/*
	 *Кадровий резерв на посаду
	 */
	@Column(name = "personnelProvisionForPost")
	String personnelProvisionForPost;
	
	
	@Column(name = "biography")
	String biography;
	
	@Column(name = "photoMainStaffs")
	@OneToMany
	List<MainStaffPhotos> photoMainStaffs;
	
	@Column(name = "documents")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<StuffDocuments> documents;


}
