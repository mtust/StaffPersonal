package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Other;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherRepository extends JpaRepository<Other, Long> {

}
