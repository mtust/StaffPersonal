package com.staff.personal.service.nominallyJobBook;

import com.staff.personal.domain.nominallyJobBooks.Worker;
import com.staff.personal.dto.RestMessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@Service
public interface WorkerService {

    List<Worker> getWorkers();

    Worker getWorker(Long id);

    List<Worker> getWorkersByNominallyJobBook(Long nominallyJobBookId);

    RestMessageDTO createWorkerForNominalJobBookId(Worker worker, Long nominalJobBookId);

    RestMessageDTO editWorker(Worker worker);

    RestMessageDTO deleteWorker(Long id);
}
