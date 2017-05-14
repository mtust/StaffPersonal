package com.staff.personal.repository.NominallyJobBook;

import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by mtustanovskyy on 1/22/17.
 */
@Repository
public interface NominallyJobBookParentRepository extends JpaRepository<NominallyJobBookParent, Long>{

    NominallyJobBookParent findOneByNominallyJobBooksIn(Collection<NominallyJobBook> nominallyJobBooks);

    NominallyJobBookParent findOneByNominallyJobBooksContains(NominallyJobBook nominallyJobBook);

}
