package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

}
