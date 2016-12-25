package com.staff.personal.service.impl;

import com.staff.personal.domain.Education;
import com.staff.personal.domain.Staff;
import com.staff.personal.domain.WorkExperience;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.WorkExperienceDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
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
    private WorkExperienceRepository workExperienceRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");

    @Autowired
    private StaffRepository staffRepository;


    @Override
    @Transactional
    public List<WorkExperience> getWorkExperiences(Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        return staff.getWorkExperiences();
    }

    @Override
    @Transactional
    public RestMessageDTO crateWorkExperience(List<WorkExperienceDTO> workExperienceDTOList, Long id) {
        log.info("in crateWorkExperience");
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        if (workExperienceDTOList != null) {
            try {
                List<WorkExperience> list = new ArrayList<WorkExperience>();
                for (WorkExperienceDTO workExperienceDTO : workExperienceDTOList) {
                    WorkExperience workExperience = new WorkExperience();
                    workExperience.setName(workExperienceDTO.getOrgName());
                    try {
                        workExperience.setFromDate(simpleDateFormat.parse(workExperienceDTO.getFromDate()));
                        workExperience.setToDate(simpleDateFormat.parse(workExperienceDTO.getToDate()));
                    } catch (ParseException e) {
                        workExperience.setFromDate(simpleDateFormatNew.parse(workExperienceDTO.getFromDate()));
                        workExperience.setToDate(simpleDateFormatNew.parse(workExperienceDTO.getToDate()));
                    }
                    list.add(workExperience);
                }
                log.info("list after foreach \n" + list.toString());
                staff.setWorkExperiences(list);
                workExperienceRepository.save(list);
                staffRepository.saveAndFlush(staff);
            } catch (ParseException e) {
                log.warn(e.getMessage());
                throw new BadRequestParametersException("Дата у не вірному форматі");
            }
        }
        return new RestMessageDTO("succes", true);
    }

    @Override
    public List<WorkExperience> createWorkExperienceFromDTO(List<WorkExperienceDTO> workExperienceDTOS) {
        List<WorkExperience> list = new ArrayList<WorkExperience>();
        if (workExperienceDTOS != null) {
            try {
                for (WorkExperienceDTO workExperienceDTO : workExperienceDTOS) {
                    WorkExperience workExperience = new WorkExperience();
                    workExperience.setId(workExperienceDTO.getId());
                    workExperience.setName(workExperienceDTO.getOrgName());
                    try {
                        workExperience.setFromDate(simpleDateFormat.parse(workExperienceDTO.getFromDate()));
                        workExperience.setToDate(simpleDateFormat.parse(workExperienceDTO.getToDate()));
                    } catch (ParseException e) {
                        workExperience.setFromDate(simpleDateFormatNew.parse(workExperienceDTO.getFromDate()));
                        workExperience.setToDate(simpleDateFormatNew.parse(workExperienceDTO.getToDate()));
                    }
                    list.add(workExperience);
                }
            } catch (ParseException e) {
                log.warn(e.getMessage());
                throw new BadRequestParametersException("Дата у не вірному форматі");
            }
        }
        return list;
    }


    @Override
    @Transactional
    public RestMessageDTO delWorkExperiences(Long id, Long idExp) {
        log.info("delWorkExperiences");
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        List<WorkExperience> list = staff.getWorkExperiences();
        for (WorkExperience workExperience : list) {
            if (workExperience.getId() == idExp) {
                list.remove(workExperience);
                workExperienceRepository.delete(workExperience);
                break;
            }
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<WorkExperienceDTO> createWorkExperienceDTO(List<WorkExperience> list) {
        List<WorkExperienceDTO> experienceDTOs = new ArrayList<>();
        for (WorkExperience workExperience : list) {
            WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
            workExperienceDTO.setId(workExperience.getId());
            workExperienceDTO.setOrgName(workExperience.getName());
            if (workExperience.getToDate() != null) {
                workExperienceDTO.setToDate(simpleDateFormat.format(workExperience.getToDate()));
            }
            if (workExperience.getFromDate() != null) {
                workExperienceDTO.setFromDate(simpleDateFormat.format(workExperience.getFromDate()));
            }
            experienceDTOs.add(workExperienceDTO);
        }
        return experienceDTOs;
    }


    @Override
    public List<WorkExperience> updateWorkExperiances(List<WorkExperienceDTO> workExperienceDTOS) {
        List<WorkExperience> workExperiences = this.createWorkExperienceFromDTO(workExperienceDTOS);
        return workExperienceRepository.save(workExperiences);
    }
}
