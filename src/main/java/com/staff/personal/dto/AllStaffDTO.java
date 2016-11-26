package com.staff.personal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staff.personal.domain.*;
import lombok.Data;

import java.util.List;

/**
 * Created by mtustanovskyy on 11/26/16.
 */
@Data
public class AllStaffDTO {

    private MainStaffDTO mainStaffDTO;
    private EducationDTO educationDTO;
    private List<WorkExperienceDTO> workExperienceDTOs;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;
    private List<BenefitsDTO> benefits;
    private List<PromotionDTO> promotions;
    private List<HolidayDTO> holidays;
    private List<PremiumFineDTO> premiumFines;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FiredDTO fired;
    private Other other;
    private List<HospitalsDTo> hospitals;

}
