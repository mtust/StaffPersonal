package com.staff.personal.service.impl;

import com.staff.personal.domain.Education;
import com.staff.personal.domain.MainEducationBlock;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.EducationDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.EducationRepository;
import com.staff.personal.repository.MainEducationBlockRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nazar on 08.11.16.
 */
@Slf4j
@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    EducationRepository educationRepository;
    @Autowired
    MainEducationBlockRepository mainEducationRepository;
    @Autowired
    StaffRepository staffRepository;

    @Override
    @Transactional
    public RestMessageDTO createEducation(EducationDTO educationDTO, Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        } else {
            Education education = new Education();
            List<MainEducationBlock> mainEducationBlocks = educationDTO.getMainEducationBlocks();
            education.setLanguage(educationDTO.getLanguage());
            education.setOtherStudying(educationDTO.getOtherStudying());
            education.setMainEducationBlocks(mainEducationBlocks);
            staff.setEducation(education);
            staffRepository.save(staff);
            return new RestMessageDTO("Succes", true);
        }
    }

    @Override
    @Transactional
    public Education getEducation(Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        log.info("getEducation");
        Education education = staff.getEducation();
        log.info(education.toString());
        return education;
    }

    @Override
    @Transactional
    public RestMessageDTO delEducation(Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        log.info("delEducation");
        Education education = staff.getEducation();
        educationRepository.delete(education);
        education.setMainEducationBlocks(null);
        staff.setEducation(null);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public RestMessageDTO delMainEducation(Long idStuff, Long idMainEducation) {
        Staff staff = staffRepository.findOne(idStuff);
        log.info("delEducation");
        Education education = staff.getEducation();
        educationRepository.delete(education);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }

}


