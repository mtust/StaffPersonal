package com.staff.personal.controller.api;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.dto.CreateMainStaffDTO;
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
    RestMessageDTO createMainStaff(@RequestBody CreateMainStaffDTO createMainStaffDTO){
        log.info("IN createMainStaff Controller");
        log.info(createMainStaffDTO.toString());
    return  staffService.createMainStaff(createMainStaffDTO);
    }
    @RequestMapping(value = "deleteMainStaff", method = RequestMethod.DELETE)
    RestMessageDTO deleteMainStaff(@RequestParam(value = "id") Long mainStaffId) {
        return staffService.deleteMainStaffById(mainStaffId);
    }



}