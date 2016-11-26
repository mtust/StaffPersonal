package com.staff.personal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staff.personal.domain.*;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by mtustanovskyy on 11/26/16.
 */
@Data
public class GetAllStaffDTO {

    Long id;
    private MainStaff mainStaff;
    private Education education;
    private List<WorkExperience> workExperiences;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;
    private List<Benefits> benefits;
    private List<Promotion> promotions;
    private List<Holiday> holidays;
    private List<PremiumFine> premiumFines;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Fired fired;
    private Other other;
    private Boolean isDeleted;

}
