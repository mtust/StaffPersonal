package com.staff.personal.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Slf4j
@Entity
@Table(name = "mainEducationBlock")
public class MainEducationBlock {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

    @Column(name = "educationalLevel")
    String educationalLevel;// Освітний рівень??

    @Column(name = "school")
    private String school;

    @Column(name = "educationLevel")
    private String educationQualificationLevel;// Освітньо-кваліфікаційний рівень??

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "SeriesAndNumberOfDiploma")
    private String seriesAndNumberOfDiploma;


}
