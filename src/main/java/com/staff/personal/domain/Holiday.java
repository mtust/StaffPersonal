package com.staff.personal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "holiday")
public class Holiday implements Comparable<Holiday>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "typeHoliday")
	private String typeHoliday;

	@Column(name = "holidayPlace")
	private String holidayPlace;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fromDate")
	private Date fromDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "toDate")
	private Date toDate;

	@Column(name = "description")
	private String description;

	@Override
	public int compareTo(Holiday o) {
		return getFromDate().compareTo(o.getFromDate());
	}
}
