package com.staff.personal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staff.personal.domain.Education;
import com.staff.personal.domain.Region;
import lombok.Data;

import java.util.List;

/**
 * Created by nazar on 15.11.16.
 */
@Data
public class GetStaffDTO {

    Long id;
    private MainStaffDTO mainStaffDTO;
    private Education education;
    private List<WorkExperienceDTO> workExperiences;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

}
