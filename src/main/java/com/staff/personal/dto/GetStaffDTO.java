package com.staff.personal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staff.personal.domain.Education;
import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Region;
import com.staff.personal.domain.WorkExperience;
import lombok.Data;

import java.util.List;

/**
 * Created by nazar on 15.11.16.
 */
@Data
public class GetStaffDTO {

    Long id;
    private MainStaff mainStaff;
    private Education education;
    private List<WorkExperience> workExperiences;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

}
