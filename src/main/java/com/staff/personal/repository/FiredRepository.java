package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Fired;

public interface FiredRepository extends JpaRepository<Fired, Long> {

}
