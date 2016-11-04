package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.MainStaff;
import org.springframework.stereotype.Repository;

@Repository
public interface MainStaffRepository extends JpaRepository<MainStaff, Long> {

}
