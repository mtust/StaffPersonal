package com.staff.personal.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "stuffUser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Email
	private String email;
	private String password;
	private Boolean isEnabled;

	@Enumerated(EnumType.ORDINAL)
	private Role role;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private UserPhotos photo;

	@ManyToOne(cascade = CascadeType.ALL)
	private Region region;
}
