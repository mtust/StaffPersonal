package com.staff.personal.controller.api;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.dto.MainStaffDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nazar on 04.11.16.
 */
@Slf4j
@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class MainStaffController {

    @Autowired
    StaffService staffService;

    @RequestMapping(value = "getAllMainStaff", method = RequestMethod.GET)
    List<MainStaff> getAllMainStaff(){
        log.info("IN getAllMainStaff Controller");
        return staffService.getAllMainStaff();
    }

    @RequestMapping(value = "createMainStaff", method = RequestMethod.POST)
    RestMessageDTO createMainStaff(@RequestBody MainStaffDTO mainStaffDTO){
        log.info("IN createMainStaff Controller");
        log.info(mainStaffDTO.toString());
    return  staffService.createMainStaff(mainStaffDTO);
    }
    @RequestMapping(value = "deleteMainStaff/{id}", method = RequestMethod.DELETE)
    RestMessageDTO deleteMainStaff(@PathVariable  Long id) {
        return staffService.deleteMainStaffById(id);
    }



}
