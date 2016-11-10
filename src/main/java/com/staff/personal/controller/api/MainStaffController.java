package com.staff.personal.controller.api;

import com.staff.personal.domain.Education;
import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.WorkExperience;
import com.staff.personal.dto.EducationDTO;
import com.staff.personal.dto.MainStaffDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.WorkExperienceDTO;
import com.staff.personal.service.EducationService;
import com.staff.personal.service.StaffService;
import com.staff.personal.service.WorkExperienceService;
import com.sun.org.apache.regexp.internal.RE;
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
    @Autowired
    EducationService educationService;
    @Autowired
    WorkExperienceService workExperienceService;


    //MAIN STAFF
    @RequestMapping(value = "mainStaff", method = RequestMethod.GET)
    List<MainStaff> getAllMainStaff(){
        log.info("IN getAllMainStaff Controller");
        return staffService.getAllMainStaff();
    }

    @RequestMapping(value = "mainStaff", method = RequestMethod.POST)
    RestMessageDTO createMainStaff(@RequestBody MainStaffDTO mainStaffDTO){
        log.info("IN createMainStaff Controller");
        log.info(mainStaffDTO.toString());
    return  staffService.createMainStaff(mainStaffDTO);
    }
    @RequestMapping(value = "mainStaff/{id}", method = RequestMethod.DELETE)
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
