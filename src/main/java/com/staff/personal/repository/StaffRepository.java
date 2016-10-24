package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

}
