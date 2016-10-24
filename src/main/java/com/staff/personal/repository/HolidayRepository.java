package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

}
