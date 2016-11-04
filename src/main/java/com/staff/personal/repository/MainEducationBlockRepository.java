package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.MainEducationBlock;
import org.springframework.stereotype.Repository;

@Repository
public interface MainEducationBlockRepository extends JpaRepository<MainEducationBlock, Long>{

}
