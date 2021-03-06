package com.staff.personal.dto;

import com.staff.personal.domain.StuffDocuments;
import lombok.Data;

/**
 * Created by nazar on 14.11.16.
 */
@Data
public class BenefitsDTO {
    private Long id;
    private String name;
    private String fromDate;
    private String toDate;
    private String orderPerson;
    private String orderDate;
    private String orderNumber;
    private StuffDocuments act;
    private String docNumber;
    private String benefitWorksYears;
    private String benefitWorksMonths;
    private String benefitWorksDays;
}
