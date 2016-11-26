package com.staff.personal.domain;


import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "fired")
public class Fired {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "dateFired")
	private Date dateFiring;
	
	@Column(name = "orderNumber")
	private String orderNumber;
   /**
	*інформація, куди саме
	*/
	@Column(name = "whereFired")
   	private String whereFired;
	/**
	*Пункт (стаття) звільнення
	*/
	@Column(name = "article")
	private String article;
	
	
	@Column(name = "lastPosition")
	private String lastPosition;
	
	/**
	*Спеціальне звання
	*/
	@Column(name = "specialRank")
	private String specialRank;
	/**
	*Військовий облік
	*/
	@Column(name = "militaryAccount")
	private String militaryAccount ;
	/**
	*Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
	*/
	@Column(name = "referenceLEKCertificate")
	private String referenceLEKCertificate;//??????????mb Document??
	/**
	*Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
	*/
	@JsonIgnore
	@Column(name = "referenceLEKDate")
	private Date referenceLEKDate;
	/**
	*Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)
	*/
	@Column(name = "referenceLEKNumber")
	private String referenceLEKNumber;
	
	@Column(name = "conclusion")
	private String conclusion;
	/**
	*Вислуга років на стан звільнення: календарна; навчання у ВНЗ; пільгова; трудовий стаж
	*/
	@Column(name = "seniority")//
	private String seniority;
	
	/**
	*Особову справу направлено до (куди саме)
	*/
	@Column(name = "personalFileForwarded")
	private String personalFileForwarded;
	

}
