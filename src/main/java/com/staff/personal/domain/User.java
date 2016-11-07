package com.staff.personal.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

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
	private UserPhoto photo;

	@ManyToOne(cascade = CascadeType.ALL)
	private Region region;


	public User(){}

}
