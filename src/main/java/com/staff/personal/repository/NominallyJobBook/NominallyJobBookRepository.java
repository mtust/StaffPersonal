package com.staff.personal.repository.NominallyJobBook;

import com.staff.personal.domain.Region;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@Repository
public interface NominallyJobBookRepository extends JpaRepository<NominallyJobBook, Long>{

    List<NominallyJobBook> findOneByRegionIn(Collection<Region> regions);

    List<NominallyJobBook> findByRegionId(Long regionId);

    List<NominallyJobBook> findByCode(String code);
}
