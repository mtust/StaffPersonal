package com.staff.personal.dto;

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
    private String order;
    private String orderDate;
    private String certification;
    private String privilege;
    private String actsAndComments;
    private String otherInfo;
}
