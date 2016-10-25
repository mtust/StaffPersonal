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

	@Column(name = "whereFired")//(інформація, куди саме)
	String whereFired;
	
	@Column(name = "article")//Пункт (стаття) звільнення
	String article;
	
	
	@Column(name = "lastPosition")
	String lastPosition;
	
	
	@Column(name = "specialRank")//Спеціальне звання
	String specialRank;
	
	@Column(name = "militaryAccount")//Військовий облік????
	String militaryAccount ;
	
	@Column(name = "groupRemuneration")//Довідка ЦЛЕК (ЛЕК) (свідоцтво) (дата, номер)?????
	String referenceLEK;
	
	@Column(name = "conclusion")
	String conclusion;
	
//	@Column(name = "")//Вислуга років на стан звільнення: календарна; навчання у ВНЗ; пільгова; трудовий стаж
//	String ;
	
	@Column(name = "groupRemuneration")//Особову справу направлено до (куди саме)
	String personalFileForwarded;
	

}
