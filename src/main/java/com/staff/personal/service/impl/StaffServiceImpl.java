package com.staff.personal.service.impl;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.MainStaffDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.StaffDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.repository.MainStaffRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.EducationService;
import com.staff.personal.service.StaffService;
import com.staff.personal.service.WorkExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by nazar on 04.11.16.
 */
@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    WorkExperienceService workExperienceService;

    @Autowired
    EducationService educationService;

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
    public RestMessageDTO createMainStaff(MainStaffDTO mainStaffDTO, Long id) {
        log.info("IN createMainStaff");
        log.info(mainStaffDTO.toString());
        try {
            Staff staff = staffRepository.findOne(id);
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
    public RestMessageDTO createStaff(StaffDTO staffDTO) {
        Staff staff = new Staff();
        staff = staffRepository.save(staff);
        this.createMainStaff(staffDTO.getMainStaffDTO(), staff.getId());
        workExperienceService.crateWorkExperience(staffDTO.getWorkExperienceDTOs(), staff.getId());
        educationService.createEducation(staffDTO.getEducationDTO(), staff.getId());
        return new RestMessageDTO("Success", true);
    }

    @Override
    public RestMessageDTO deleteStaff(Long id) {
        staffRepository.delete(id);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public Staff getStaff(Long id){
        return staffRepository.findOne(id);
    }
}
