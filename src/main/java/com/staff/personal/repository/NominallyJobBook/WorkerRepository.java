package com.staff.personal.repository.NominallyJobBook;

import com.staff.personal.domain.nominallyJobBooks.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long>{
}
