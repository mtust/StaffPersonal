package com.staff.personal.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by nazar on 17.11.16.
 */
@Data
public class HospitalsDTo {

    private String id;

    private String typeHospital;

    private String fromDate;

    private String toDate;

    private String description;

}
