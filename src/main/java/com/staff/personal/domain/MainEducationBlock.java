package com.staff.personal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Slf4j
@Entity
@Table(name = "mainEducationBlock")
public class MainEducationBlock implements Serializable{
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "educationalLevel")
    private String educationalLevel;// Освітний рівень??

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
