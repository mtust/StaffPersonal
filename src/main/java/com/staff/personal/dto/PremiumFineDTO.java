package com.staff.personal.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by nazar on 18.11.16.
 */
@Data
public class PremiumFineDTO {
    private String id;

    private String name;

    private String order;

    private String orderDate;

    private String type;

    private String serialNumber; // !!!!!!!!!!!!
}
