package com.staff.personal.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Data
@Entity
@Table(name = "education")
public class Education implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MainEducationBlock> mainEducationBlocks;

	private String otherStudying;

	private String language;






}
