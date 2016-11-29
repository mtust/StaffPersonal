package com.staff.personal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staff.personal.domain.*;
import lombok.Data;

import java.util.List;

/**
 * Created by mtustanovskyy on 11/26/16.
 */
@Data
public class GetAllStaffDTO {

    Long id;
    private MainStaffDTO mainStaff;
    private Education education;
    private List<WorkExperienceDTO> workExperiences;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;
    private List<BenefitsDTO> benefits;
    private List<PromotionDTO> promotions;
    private List<HolidayDTO> holidays;
    private List<HospitalsDTO> hospitals;
    private List<PremiumFineDTO> premiumFines;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FiredDTO fired;
    private Other other;
    private Boolean isDeleted;

}
