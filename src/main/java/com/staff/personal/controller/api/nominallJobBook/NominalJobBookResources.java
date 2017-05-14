package com.staff.personal.controller.api.nominallJobBook;

import com.staff.personal.domain.Role;
import com.staff.personal.domain.Staff;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import com.staff.personal.domain.nominallyJobBooks.Position;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.nominallyJobBook.ParentNominallyJobBookDTO;
import com.staff.personal.dto.nominallyJobBook.PoorNominallyJobBookDTO;
import com.staff.personal.security.Secured;
import com.staff.personal.service.StaffService;
import com.staff.personal.service.nominallyJobBook.NominallyJobBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private StaffService staffService;


    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<NominallyJobBook> getNominalJobBooks(){
        return nominallyJobBookService.getNominallyJobBooks();
    }

    @RequestMapping(value = "{id}/staff", method = RequestMethod.GET)
    public List<Staff> getStaffByNominallyJobBook(@PathVariable(value = "id") Long nominallyJobBookId){
        return nominallyJobBookService.getStaffByNominallyJobBook(nominallyJobBookId);
    }
    @RequestMapping(value = "staff", method = RequestMethod.GET)
    public List<Staff> getStaffByPositionCode(@RequestParam(value = "code") String code){
        return staffService.getStaffByPositionCode(code);
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


    @RequestMapping(value = "withoutPostition", method = RequestMethod.POST)
    public RestMessageDTO createNominalJobBook(@RequestBody PoorNominallyJobBookDTO poorNominallyJobBookDTO, @RequestParam Long parentId){
        return nominallyJobBookService.createNominalJobBook(poorNominallyJobBookDTO,parentId );
    }


    @RequestMapping(value = "withoutPostition/{id}", method = RequestMethod.PUT)
    public RestMessageDTO editNominalJobBook(@RequestBody PoorNominallyJobBookDTO poorNominallyJobBookDTO, @PathVariable Long id){
        poorNominallyJobBookDTO.setId(id);
        return nominallyJobBookService.editNominalJobBook(poorNominallyJobBookDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public RestMessageDTO updateNominalJobBook(@RequestBody NominallyJobBook nominallyJobBook, @PathVariable Long id){
        return nominallyJobBookService.updateNominalJobBook(nominallyJobBook, id);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestMessageDTO deleteNominalJobBook(@PathVariable Long id){
        return nominallyJobBookService.deleteNominalJobBook(id);
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

    @RequestMapping(value = "parent/{id}", method = RequestMethod.PUT)
    public RestMessageDTO updateParent(@PathVariable(value = "id") Long parentId, @RequestBody ParentNominallyJobBookDTO parentNominallyJobBook){
        return nominallyJobBookService.updateParent(parentId, parentNominallyJobBook);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestMessageDTO createNominalJobBookWithPosition(@RequestBody NominallyJobBook nominallyJobBook, @RequestParam Long parentId){
        List<Long> positionsId = new ArrayList<>();
        for(Position position : nominallyJobBook.getPositions()){
            positionsId.add(position.getId());
        }
        nominallyJobBook = nominallyJobBookService.createNominalJobBook(nominallyJobBook,parentId );
        return nominallyJobBookService.addPosition(nominallyJobBook.getId(), positionsId);
    }

    @RequestMapping(value = "{id}/addPosition", method = RequestMethod.POST)
    public RestMessageDTO addPosition(@PathVariable("id") Long id, @RequestBody List<Long> positionIds){
        return nominallyJobBookService.addPosition(id, positionIds);
    }

    @RequestMapping(value = "parent/{id}", method = RequestMethod.DELETE)
    public RestMessageDTO deleteParentNominalJobBook(@PathVariable("id") Long id){
        return nominallyJobBookService.deleteParent(id);
    }




}
