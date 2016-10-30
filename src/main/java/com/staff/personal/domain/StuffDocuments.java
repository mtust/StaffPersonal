package com.staff.personal.domain;

import lombok.Data;

import java.sql.Blob;

import javax.persistence.*;

@Entity
@Data
@Table
public class StuffDocuments {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	@Lob
	private Blob file;

}
