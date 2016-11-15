package com.staff.personal.dto;

import com.staff.personal.domain.Education;
import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.WorkExperience;
import lombok.Data;

import java.util.List;

/**
 * Created by nazar on 15.11.16.
 */
@Data
public class GetStaffDTO {

    private MainStaff mainStaff;
    private Education education;
    private List<WorkExperience> workExperiences;

}
