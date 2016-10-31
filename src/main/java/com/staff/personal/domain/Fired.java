package com.staff.personal.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Fired {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "dateFired")
	Date dateFiring;
	
	@Column(name = "orderNumber")
	String orderNumber;
   /**
	*інформація, куди саме
	*/
	@Column(name = "whereFired")
	String whereFired;
	/**
	*Пункт (стаття) звільнення
	*/
	@Column(name = "article")
	String article;
	
	
	@Column(name = "lastPosition")
	String lastPosition;
	
	/**
	*Спеціальне звання
	*/
	@Column(name = "specialRank")
	String specialRank;
	/**
	*Військовий облік
	*/
	@Column(name = "militaryAccount")
	String militaryAccount ;
	/**
	*Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
	*/
	@Column(name = "referenceLEKCertificate")
	String referenceLEKCertificate;//??????????mb Document??
	/**
	*Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
	*/
	@Column(name = "referenceLEKDate")
	Date referenceLEKDate;
	/**
	*Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
	*/
	@Column(name = "referenceLEKNumber")
	String referenceLEKNumber;
	
	@Column(name = "conclusion")
	String conclusion;
	/**
	*Вислуга років на стан звільнення: календарна; навчання у ВНЗ; пільгова; трудовий стаж
	*/
	@Column(name = "seniority")//
	String seniority;
	
	/**
	*Особову справу направлено до (куди саме)
	*/
	@Column(name = "personalFileForwarded")
	String personalFileForwarded;
	

}
