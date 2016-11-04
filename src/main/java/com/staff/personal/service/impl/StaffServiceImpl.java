package com.staff.personal.service.impl;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.CreateMainStaffDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.repository.MainStaffRepository;
import com.staff.personal.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nazar on 04.11.16.
 */
@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    MainStaffRepository mainStaffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public List<Staff> getAllStaff() {
        return null;
    }


    @Override
    public RestMessageDTO deleteData(Long dataId) {
        return null;
    }

    @Override
    public RestMessageDTO createMainStaff(CreateMainStaffDTO createMainStaffDTO) {
        log.info("IN createMainStaff");
        try {
            MainStaff mainStaff = new MainStaff();
            mainStaff.setFullName(createMainStaffDTO.getFullName());
            mainStaff.setSpecialRank(createMainStaffDTO.getSpecialRank());
            mainStaff.setDateOfBirth(simpleDateFormat.parse(createMainStaffDTO.getDateOfBirth()));
            mainStaff.setPosition(createMainStaffDTO.getPosition());
            mainStaff.setNumberConferringSpeclRanks(createMainStaffDTO.getNumberConferringSpeclRanks());
            mainStaff.setDateNumberPurpose(simpleDateFormat.parse(createMainStaffDTO.getDateNumberPurpose()));
            mainStaff.setPhoneNumber(createMainStaffDTO.getPhoneNumber());
            mainStaff.setContractFromDate(simpleDateFormat.parse(createMainStaffDTO.getContractFromDate()));
            mainStaff.setContractToDate(simpleDateFormat.parse(createMainStaffDTO.getContractToDate()));
            mainStaff.setExemptionDate(simpleDateFormat.parse(createMainStaffDTO.getExemptionDate()));
            mainStaff.setExemptionNumOrder(createMainStaffDTO.getExemptionNumOrder());
            mainStaff.setInCommand(createMainStaffDTO.getInCommand());
            mainStaff.setDateSwear(simpleDateFormat.parse(createMainStaffDTO.getDateSwear()));
            mainStaff.setRankCivilServant(createMainStaffDTO.getRankCivilServant());
            mainStaff.setCategoriesCivilServants(createMainStaffDTO.getCategoriesCivilServants());
            mainStaff.setGroupRemuneration(createMainStaffDTO.getGroupRemuneration());
            mainStaff.setGroupRemuneration(createMainStaffDTO.getGroupRemuneration());
            mainStaff.setStaffOfficerCategory(createMainStaffDTO.getStaffOfficerCategory());
            mainStaff.setLastCertification(simpleDateFormat.parse(createMainStaffDTO.getLastCertification()));
            mainStaff.setConcludedCertification(createMainStaffDTO.getConcludedCertification());
            mainStaff.setPersonnelProvisionForPost(createMainStaffDTO.getPersonnelProvisionForPost());
            mainStaff.setBiography(createMainStaffDTO.getBiography());
            log.info(mainStaff.toString());
            mainStaffRepository.save(mainStaff);
        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }

        return new RestMessageDTO("Succes", true);
    }
}
