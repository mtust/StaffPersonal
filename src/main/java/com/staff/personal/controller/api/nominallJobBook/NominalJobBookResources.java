package com.staff.personal.controller.api.nominallJobBook;

import com.staff.personal.domain.Role;
import com.staff.personal.domain.Staff;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.nominallyJobBook.ParentNominallyJobBookDTO;
import com.staff.personal.dto.nominallyJobBook.PoorNominallyJobBookDTO;
import com.staff.personal.security.Secured;
import com.staff.personal.service.StaffService;
import com.staff.personal.service.nominallyJobBook.NominallyJobBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "{code}/staff", method = RequestMethod.GET)
    public List<Staff> getStaffByPositionCode(@PathVariable(value = "code") String code){
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


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public RestMessageDTO editNominalJobBook(@RequestBody PoorNominallyJobBookDTO poorNominallyJobBookDTO, @PathVariable Long id){
        poorNominallyJobBookDTO.setId(id);
        return nominallyJobBookService.editNominalJobBook(poorNominallyJobBookDTO);
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
        return nominallyJobBookService.createNominalJobBook(nominallyJobBook,parentId );
    }

}
