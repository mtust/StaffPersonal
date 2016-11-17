package com.staff.personal.dto;

import lombok.Data;

/**
 * Created by nazar on 17.11.16.
 */
@Data
public class HolidayDTO {

    private String id;

    private String typeHoliday;

    private String holidayPlace;

    private String fromDate;

    private String toDate;

    private String description;
}
