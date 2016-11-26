package com.staff.personal.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by mtustanovskyy on 11/12/16.
 */
@Data
public class StaffDTO {

    private MainStaffDTO mainStaffDTO;
    private EducationDTO educationDTO;
    private List<WorkExperienceDTO> workExperienceDTOs;
    private Long regionId;


}
