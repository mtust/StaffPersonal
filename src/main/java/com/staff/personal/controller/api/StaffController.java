package com.staff.personal.controller.api;

import com.staff.personal.domain.Education;
import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Staff;
import com.staff.personal.domain.WorkExperience;
import com.staff.personal.dto.*;
import com.staff.personal.service.EducationService;
import com.staff.personal.service.StaffService;
import com.staff.personal.service.WorkExperienceService;
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
public class StaffController {

    @Autowired
    StaffService staffService;
    @Autowired
    EducationService educationService;
    @Autowired
    WorkExperienceService workExperienceService;

    @RequestMapping(method = RequestMethod.POST)
    RestMessageDTO createStaff(@RequestBody StaffDTO staffDTO){
        return  staffService.createStaff(staffDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    RestMessageDTO deleteStaff(@PathVariable Long id){
        return staffService.deleteStaff(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Staff getStaff(@PathVariable Long id){
        return staffService.getStaff(id);
    }

    //MAIN STAFF
    @RequestMapping(value = "{id}/mainStaff", method = RequestMethod.GET)
    MainStaff getMainStaffForStuff(@PathVariable Long id){
        log.info("IN getAllMainStaff Controller");
        return staffService.getMainStaffForStuff(id);
    }

    @RequestMapping(value = "{id}/mainStaff", method = RequestMethod.POST)
    RestMessageDTO createMainStaff(@PathVariable Long id, @RequestBody MainStaffDTO mainStaffDTO){
        log.info("IN createMainStaff Controller");
        log.info(mainStaffDTO.toString());
    return  staffService.createMainStaff(mainStaffDTO, id);
    }
    @RequestMapping(value = "{id}/mainStaff", method = RequestMethod.DELETE)
    RestMessageDTO deleteMainStaff(@PathVariable  Long id) {
        return staffService.deleteMainStaffById(id);
    }


    //EDUCATION
    @RequestMapping(value = "{id}/education", method = RequestMethod.POST)
    RestMessageDTO createEducation(@PathVariable  Long id, @RequestBody EducationDTO educationDTO){
       return educationService.createEducation(educationDTO, id);
    }

    @RequestMapping(value = "{id}/education", method = RequestMethod.GET)
    Education getEducationByStaffId(@PathVariable  Long id){
        log.info("getEducationByStaffId");
        return educationService.getEducation(id);
    }

    @RequestMapping(value = "{id}/education", method = RequestMethod.DELETE)
    RestMessageDTO delEducationByStaffId(@PathVariable  Long id){
        log.info("delEducationByStaffId");
        return educationService.delEducation(id);
    }

    //WORK EXPERIENCE
    @RequestMapping(value = "{id}/workExperience", method = RequestMethod.POST)
    RestMessageDTO createEducation(@PathVariable  Long id, @RequestBody List<WorkExperienceDTO> workExperienceDTOs){
        log.info("in createEducation");
        return workExperienceService.crateWorkExperience(workExperienceDTOs, id);
    }

    @RequestMapping(value = "{id}/workExperience", method = RequestMethod.GET)
    List<WorkExperience> getWorkExperience(@PathVariable  Long id){
        log.info("in getWorkExperience");
        return workExperienceService.getWorkExperiences(id);
    }

    @RequestMapping(value = "{id}/workExperience", method = RequestMethod.DELETE)
    RestMessageDTO delWorkExperience(@PathVariable  Long id){
        log.info("delWorkExperience");
        return workExperienceService.delWorkExperiences(id);
    }
}
