package com.staff.personal.service.impl;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Region;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.*;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.MainStaffRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.repository.UserRepository;
import com.staff.personal.service.EducationService;
import com.staff.personal.service.StaffService;
import com.staff.personal.service.UserService;
import com.staff.personal.service.WorkExperienceService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nazar on 04.11.16.
 */
@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private HttpServletRequest requestContext;

    @Autowired
    private UserService userService;

    @Autowired
    MainStaffRepository mainStaffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public MainStaff getMainStaffForStuff(Long id) {
        log.info("IN getAllMainStaff Controller");
        return staffRepository.findOne(id).getMainStaff();
    }


    @Override
    public RestMessageDTO deleteMainStaffById(Long dataId) {
        Staff staff = staffRepository.findOne(dataId);
        staff.setMainStaff(null);
        staffRepository.save(staff);
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO createMainStaff(MainStaffDTO mainStaffDTO, Long id) {
        log.info("IN createMainStaff");
        log.info(mainStaffDTO.toString());
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        try {
            MainStaff mainStaff = new MainStaff();
            mainStaff.setFullName(mainStaffDTO.getFullName());
            mainStaff.setSpecialRank(mainStaffDTO.getSpecialRank());
            mainStaff.setDateOfBirth(simpleDateFormat.parse(mainStaffDTO.getDateOfBirth()));
            mainStaff.setPosition(mainStaffDTO.getPosition());
            mainStaff.setNumberConferringSpeclRanks(mainStaffDTO.getNumberConferringSpeclRanks());
            mainStaff.setDateConferringSpecRanks(simpleDateFormat.parse(mainStaffDTO.getDateConferringSpecRanks()));
            mainStaff.setDateNumberPurpose(simpleDateFormat.parse(mainStaffDTO.getDateNumberPurpose()));
            mainStaff.setPhoneNumber(mainStaffDTO.getPhoneNumber());
            mainStaff.setContractFromDate(simpleDateFormat.parse(mainStaffDTO.getContractFromDate()));
            mainStaff.setContractToDate(simpleDateFormat.parse(mainStaffDTO.getContractToDate()));
            mainStaff.setExemptionDate(simpleDateFormat.parse(mainStaffDTO.getExemptionDate()));
            mainStaff.setExemptionNumOrder(mainStaffDTO.getExemptionNumOrder());
            mainStaff.setInCommand(mainStaffDTO.getInCommand());
            mainStaff.setDateSwear(simpleDateFormat.parse(mainStaffDTO.getDateSwear()));
            mainStaff.setRankCivilServant(mainStaffDTO.getRankCivilServant());
            mainStaff.setCategoriesCivilServants(mainStaffDTO.getCategoriesCivilServants());
            mainStaff.setGroupRemuneration(mainStaffDTO.getGroupRemuneration());
            mainStaff.setGroupRemuneration(mainStaffDTO.getGroupRemuneration());
            mainStaff.setStaffOfficerCategory(mainStaffDTO.getStaffOfficerCategory());
            mainStaff.setLastCertification(simpleDateFormat.parse(mainStaffDTO.getLastCertification()));
            mainStaff.setConcludedCertification(mainStaffDTO.getConcludedCertification());
            mainStaff.setPersonnelProvisionForPost(mainStaffDTO.getPersonnelProvisionForPost());
            mainStaff.setBiography(mainStaffDTO.getBiography());
            log.info(mainStaff.toString());
            staff.setMainStaff(mainStaff);
            staffRepository.save(staff);
        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }

        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO createStaff(StaffDTO staffDTO) {
        log.info(staffDTO.toString());
        Staff staff = new Staff();
        staff = staffRepository.save(staff);
        this.createMainStaff(staffDTO.getMainStaffDTO(), staff.getId());
        workExperienceService.crateWorkExperience(staffDTO.getWorkExperienceDTOs(), staff.getId());
        educationService.createEducation(staffDTO.getEducationDTO(), staff.getId());
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public RestMessageDTO deleteStaff(Long id) {
        staffRepository.delete(id);
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public GetStaffDTO getStaff(Long id) {
        log.info("claims in service: " + ((Claims) requestContext.getAttribute("claims")).get("id"));
        Long userId = Long.parseLong(((Claims) requestContext.getAttribute("claims")).get("id").toString());
        Set<Region> regions = userService.getUserRegions(userId);
        log.info("user regions: " + regions);
        Staff staff = staffRepository.findOne(id);
        log.info("stuff regions " + staff.getRegion());
        if(staff == null || (staff.getRegion()!= null && !regions.contains(staff.getRegion()))){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }        GetStaffDTO getStaffDTO = new GetStaffDTO();
        getStaffDTO.setWorkExperiences(staff.getWorkExperiences());
        getStaffDTO.setEducation(staff.getEducation());
        getStaffDTO.setMainStaff(staff.getMainStaff());
        return getStaffDTO ;
    }
    @Transactional
    @Override
    public List<GetStaffDTO> getAllStaff(){
        List<Staff> listAll = staffRepository.findAll();
        Long userId = (Long) ((Claims) requestContext.getAttribute("claims")).get("id");
        Set<Region> regions = userService.getUserRegions(userId);
        List<Staff> list = listAll.stream().filter(staff -> regions.contains(staff.getRegion())).collect(Collectors.toList());
        List<GetStaffDTO> listDTO = new ArrayList<>();
        for (Staff staff : list) {
        GetStaffDTO getStaffDTO = new GetStaffDTO();
            getStaffDTO.setMainStaff(staff.getMainStaff());
            listDTO.add(getStaffDTO);
        }
            log.info("list after foreach \n" + listDTO.toString());

        return listDTO;
    }
}
