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

	@Column(name = "dateNumberConferringSpecialRanks")//номер дата присвоєння спеціальних звань
	String dateNumberConferringSpecialRanks;

	@Column(name = "dateNumberPurpose")//Дата призначення
	Date dateNumberPurpose;
	
	@Column(name = "phomeNumber")
	String phomeNumber;
	
	@Column(name = "dateContract")//Дати контракту: «з» та «по»???	
	String dateContract;
	
	
	@Column(name = "exemptionDateAndNumberOrder")//Звільнення зі служби цивільного захисту, дата та номер наказу
	String exemptionDateAndNumberOrder ;
	
//	@Column(name = "?????")//У розпорядженні відовідального керівника, дата та номер наказу
//	String ???????;
	
	@Column(name = "specialRank")//Дата прийняття присяги державного службовця
	Date dateSwear;
	
	@Column(name = "rankCivilServant")
	String rankCivilServant;
	
	@Column(name = "categoriesCivilServants")//Категорія державного службовця
	String categoriesCivilServants;
	
	@Column(name = "groupRemuneration")//Група оплати праці
	String groupRemuneration;
	
	@Column(name = "staffOfficerCategory")//Штатно-посадова категорія
	String staffOfficerCategory;
	
	@Column(name = "lastCertification")
	Date lastCertification;
	
	@Column(name = "concludedCertification")//Висновок атестації
	String concludedCertification;
	
	@Column(name = "personnelProvisionForPost")//Кадровий резерв на посаду
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
