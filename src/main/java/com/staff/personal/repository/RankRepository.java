package com.staff.personal.repository;

import com.staff.personal.domain.nominallyJobBooks.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}
