package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.MainStaff;

public interface MainStaffRepository extends JpaRepository<MainStaff, Long> {

}
