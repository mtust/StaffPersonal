package com.staff.personal.service;

import com.staff.personal.domain.Education;
import com.staff.personal.dto.EducationDTO;
import com.staff.personal.dto.RestMessageDTO;

/**
 * Created by nazar on 08.11.16.
 */
public interface EducationService {

    RestMessageDTO createEducation(EducationDTO educationDTO, Long id);

    Education getEducation(Long id);

    RestMessageDTO delEducation(Long id);

    RestMessageDTO delMainEducation(Long idStuff, Long idMainEducation);

    Education updateEducation(EducationDTO educationDTO);
}
