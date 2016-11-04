package com.staff.personal.repository;

import com.staff.personal.domain.PremiumFine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremiumFineRepository extends JpaRepository<PremiumFine, Long> {

}
