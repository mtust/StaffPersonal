package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.StuffDocuments;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffDocumentsRepository extends JpaRepository<StuffDocuments, Long> {

}
