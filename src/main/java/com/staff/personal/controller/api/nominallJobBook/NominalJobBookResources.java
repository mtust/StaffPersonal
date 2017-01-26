package com.staff.personal.controller.api.nominallJobBook;

import com.staff.personal.domain.Role;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import com.staff.personal.domain.nominallyJobBooks.Worker;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.nominallyJobBook.PoorNominallyJobBookDTO;
import com.staff.personal.security.Secured;
import com.staff.personal.service.nominallyJobBook.NominallyJobBookService;
import com.staff.personal.service.nominallyJobBook.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by mtustanovskyy on 1/15/17.
 */
@RestController
@Slf4j
@RequestMapping("/api/nominalJobBook")
@CrossOrigin
public class NominalJobBookResources {

    @Autowired
    private NominallyJobBookService nominallyJobBookService;

    @Autowired
    private WorkerService workerService;

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<NominallyJobBook> getNominalJobBooks(){
        return nominallyJobBookService.getNominallyJobBooks();
    }

    @RequestMapping(value = "{id}/worker", method = RequestMethod.GET)
    public List<Worker> getWorkersByNominallyJobBook(@PathVariable(value = "id") Long nominallyJobBookId){
        return workerService.getWorkersByNominallyJobBook(nominallyJobBookId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PoorNominallyJobBookDTO> getPoorNominallyJobBooks(){
        return nominallyJobBookService.getPoorNominallyJobBooks();
    }

    @RequestMapping(value = "all/{id}", method = RequestMethod.GET)
    public NominallyJobBook getNominallyJobBook(@PathVariable(value = "id") Long id){
        return nominallyJobBookService.getNominallyJobBook(id);
    }

    @RequestMapping(value = "{regionId}", method = RequestMethod.GET)
    public List<NominallyJobBook> getNominallyJobBooksByRegion(@PathVariable Long regionId){
        return nominallyJobBookService.getNominallyJobBooksByRegion(regionId);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public PoorNominallyJobBookDTO getPoorNominallyJobBook(@PathVariable(value = "id") Long id){
        return nominallyJobBookService.getPoorNominallyJobBook(id);
    }

    @RequestMapping(value = "worker", method = RequestMethod.GET)
    public List<Worker> getWorkers(){
        return workerService.getWorkers();
    }

    @RequestMapping(value = "worker/{id}", method = RequestMethod.GET)
    public Worker getWorker(@PathVariable Long id){
        return workerService.getWorker(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestMessageDTO createNominalJobBook(@RequestBody PoorNominallyJobBookDTO poorNominallyJobBookDTO){
        return nominallyJobBookService.createNominalJobBook(poorNominallyJobBookDTO);
    }

    @RequestMapping(value = "{id}/worker", method = RequestMethod.POST)
    public RestMessageDTO createWorker(@RequestBody Worker worker, @PathVariable(value = "id") Long nominalJobBookId){
        return workerService.createWorkerForNominalJobBookId(worker, nominalJobBookId);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public RestMessageDTO editNominalJobBook(@RequestBody PoorNominallyJobBookDTO poorNominallyJobBookDTO, @PathVariable Long id){
        poorNominallyJobBookDTO.setId(id);
        return nominallyJobBookService.editNominalJobBook(poorNominallyJobBookDTO);
    }

    @RequestMapping(value = "worker/{id}", method = RequestMethod.PUT)
    public RestMessageDTO editWorker(@RequestBody Worker worker, @PathVariable Long id){
        worker.setId(id);
        return workerService.editWorker(worker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestMessageDTO deleteNominalJobBook(@PathVariable Long id){
        return nominallyJobBookService.deleteNominalJobBook(id);
    }

    @RequestMapping(value = "worker/{id}", method = RequestMethod.DELETE)
    public RestMessageDTO deleteWorker(@PathVariable Long id){
        return workerService.deleteWorker(id);
    }

    @RequestMapping(value = "parent", method = RequestMethod.GET)
    public List<NominallyJobBookParent> getNominallyJobBookParent(){
        return nominallyJobBookService.getNominallyJobBookParent();
    }


    @RequestMapping(value = "parent/{id}", method = RequestMethod.GET)
    public NominallyJobBookParent getNominallyJobBookParent(@PathVariable(value = "id") Long id){
        return nominallyJobBookService.getNominallyJobBookParent(id);
    }

    @RequestMapping(value = "parent", method = RequestMethod.POST)
    public RestMessageDTO createNominallyJobBookParent(@RequestBody NominallyJobBookParent nominallyJobBookParent){
        return nominallyJobBookService.createNominalJobBookParent(nominallyJobBookParent);
    }



}
