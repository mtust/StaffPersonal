package com.staff.personal.service.nominallyJobBook.impl;

import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.Worker;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.repository.NominallyJobBook.NominallyJobBookRepository;
import com.staff.personal.repository.NominallyJobBook.WorkerRepository;
import com.staff.personal.service.nominallyJobBook.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@Service
public class WorkerServiceImpl implements WorkerService{

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private NominallyJobBookRepository nominallyJobBookRepository;

    @Override
    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public Worker getWorker(Long id) {
        return workerRepository.getOne(id);
    }

    @Override
    public List<Worker> getWorkersByNominallyJobBook(Long nominallyJobBookId) {
        NominallyJobBook nominallyJobBook = nominallyJobBookRepository.getOne(nominallyJobBookId);
        return nominallyJobBook.getWorkers();
    }

    @Override
    public RestMessageDTO createWorkerForNominalJobBookId(Worker worker, Long nominalJobBookId) {
        Worker newWorker = workerRepository.save(worker);
        NominallyJobBook nominallyJobBook = nominallyJobBookRepository.getOne(nominalJobBookId);
        List<Worker> workers = nominallyJobBook.getWorkers();
        workers.add(worker);
        nominallyJobBook.setWorkers(workers);
        nominallyJobBookRepository.save(nominallyJobBook);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public RestMessageDTO editWorker(Worker worker) {
        workerRepository.save(worker);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public RestMessageDTO deleteWorker(Long id) {
        workerRepository.delete(id);
        return new RestMessageDTO("Success", true);
    }
}
