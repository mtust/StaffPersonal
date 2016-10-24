package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Hospitals;

public interface HospitalsRepository extends JpaRepository<Hospitals, Long> {

}
