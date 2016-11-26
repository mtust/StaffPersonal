package com.staff.personal.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

@Slf4j
@Data
@Entity
@Table(name = "staff")
public class Staff implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch=FetchType.EAGER , cascade=CascadeType.ALL, orphanRemoval=true)
	private MainStaff mainStaff;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval=true)
	private Education education;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
	private Other other;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<WorkExperience> workExperiences;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Benefits> benefits;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Promotion> promotions;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Hospitals> hospitals;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Holiday> holidays;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PremiumFine> premiumFines;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Fired fired;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reports> reports;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MainStaffPhotos> mainStaffPhotos;

	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	private Region region;

//	@ColumnDefault(value = "false")
	@Column
	private Boolean isDeleted = false;
	
	
}
