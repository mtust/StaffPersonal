package com.staff.personal.repository;

import com.staff.personal.domain.nominallyJobBooks.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mtustanovskyy on 1/24/17.
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{
}
