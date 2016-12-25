package com.staff.personal.service;

import com.staff.personal.domain.WorkExperience;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.WorkExperienceDTO;

import java.util.List;

/**
 * Created by nazar on 10.11.16.
 */
public interface WorkExperienceService {

    RestMessageDTO crateWorkExperience(List<WorkExperienceDTO> workExperienceDTOList, Long id);

    List<WorkExperience> getWorkExperiences(Long id);

    RestMessageDTO delWorkExperiences(Long id, Long idExp);

    List<WorkExperienceDTO> createWorkExperienceDTO(List<WorkExperience> list);

    List<WorkExperience> createWorkExperienceFromDTO(List<WorkExperienceDTO> workExperienceDTOS);

    List<WorkExperience> updateWorkExperiances(List<WorkExperienceDTO> workExperienceDTOS);
}
