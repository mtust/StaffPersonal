package com.staff.personal.controller.api;

import com.staff.personal.domain.*;
import com.staff.personal.dto.*;
import com.staff.personal.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
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
    private StaffService staffService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private WorkExperienceService workExperienceService;
    @Autowired
    private OtherService otherService;
    @Autowired
    private MainStaffPhotoService mainStaffPhotoService;
    @Autowired
    private BenefitsService benefitsService;

    @RequestMapping(method = RequestMethod.POST)
    RestMessageDTO createStaff(@RequestBody StaffDTO staffDTO){
        return  staffService.createStaff(staffDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    RestMessageDTO deleteStaff(@PathVariable Long id){
        return staffService.deleteStaff(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    GetStaffDTO getStaff(@PathVariable Long id){
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
        log.info("delWorkExperience by id Experience!");
        return workExperienceService.delWorkExperiences(id);
    }

    //OTHER
    @RequestMapping(value = "{id}/other", method = RequestMethod.POST)
    RestMessageDTO createOter(@PathVariable Long id, @RequestBody Other other){
        log.info(log.getName());
        log.info(other.toString());
        return otherService.createOther(other,id);
    }

    @RequestMapping(value = "{id}/other", method = RequestMethod.GET)
    Other getOther(@PathVariable  Long id){
        log.info("in getWorkExperience");
        return otherService.getOther(id);
    }

    @RequestMapping(value = "{id}/other", method = RequestMethod.DELETE)
    RestMessageDTO delOther(@PathVariable  Long id){
        log.info("in getWorkExperience");
        return otherService.delOther(id);
    }

    //MAINSTAFFPHOTO
    @RequestMapping(value = "{id}/photo", headers = "content-type=multipart/form-data", method = RequestMethod.PUT)
    RestMessageDTO changePhoto(@RequestParam("photo") MultipartFile photo, @PathVariable  Long id) throws IOException {
        log.info("IN CONTROLLER changePhoto");
        return mainStaffPhotoService.addPhoto(photo, id);
    }

    @RequestMapping(value = "{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    byte[] getPhoto(@PathVariable  Long id) throws IOException, SQLException {
        log.info("IN CONTROLLER getPhoto");
        return mainStaffPhotoService.getPhoto(id);
    }


    //BENEFITS
    @RequestMapping(value = "{id}/benefits", method = RequestMethod.POST)
    RestMessageDTO addBenefits(@PathVariable  Long id, @RequestBody BenefitsDTO benefitsDTO){
        log.info("in addBenefits \n" + benefitsDTO.toString());
        return benefitsService.addBenefit(benefitsDTO,id);
    }

    @RequestMapping(value = "{id}/benefits", method = RequestMethod.GET)
    List<Benefits> getBenefits(@PathVariable  Long id){
        log.info("in getBenefits");
        return benefitsService.getBenefits(id);
    }

    @RequestMapping(value = "{id}/{idBen}/benefits", method = RequestMethod.DELETE)
    RestMessageDTO delBenefitsByID(@PathVariable  Long id, @PathVariable Long idBen){
        log.info("in delBenefitsByID");
        return benefitsService.delBenefitsById(id,idBen);
    }

}
