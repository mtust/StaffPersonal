package com.staff.personal.service.impl;

import com.staff.personal.domain.Education;
import com.staff.personal.domain.Staff;
import com.staff.personal.domain.WorkExperience;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.WorkExperienceDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.repository.WorkExperienceRepository;
import com.staff.personal.service.WorkExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar on 10.11.16.
 */
@Slf4j
@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    WorkExperienceRepository workExperienceRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    StaffRepository staffRepository;


    @Override
    @Transactional
    public List<WorkExperience> getWorkExperiences(Long id) {
        return staffRepository.findOne(id).getWorkExperiences();
    }

    @Override
    @Transactional
    public RestMessageDTO crateWorkExperience(List<WorkExperienceDTO> workExperienceDTOList, Long id) {
        log.info("in crateWorkExperience");
        try {
            List<WorkExperience> list = new ArrayList<WorkExperience>();
            for (WorkExperienceDTO workExperienceDTO : workExperienceDTOList) {
                WorkExperience workExperience = new WorkExperience();
                workExperience.setName(workExperienceDTO.getOrgName());
                workExperience.setFromDate(simpleDateFormat.parse(workExperienceDTO.getFromDate()));
                workExperience.setToDate(simpleDateFormat.parse(workExperienceDTO.getToDate()));
                list.add(workExperience);
            }
            log.info("list after foreach \n" + list.toString());
            Staff staff = staffRepository.findOne(id);
            staff.setWorkExperiences(list);
            workExperienceRepository.save(list);
            staffRepository.saveAndFlush(staff);
        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("succes", true);
    }

    @Override
    @Transactional
    public RestMessageDTO delWorkExperiences(Long id) {
        Staff staff = staffRepository.findOne(id);
        staff.setWorkExperiences(null);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }
}
