package com.staff.personal.repository;

import com.staff.personal.domain.nominallyJobBooks.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mtustanovskyy on 1/24/17.
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{

    Position findByCode(String code);

    List<Position> findByCodeContains(String code);

}
