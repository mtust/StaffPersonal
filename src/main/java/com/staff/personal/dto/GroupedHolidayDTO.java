package com.staff.personal.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.staff.personal.domain.Holiday;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class GroupedHolidayDTO {
    private Long year;
    private List<Holiday> holidays;
    private Long totalDays;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private List<Date> dates;

    public void addHoliday(Holiday holiday){
        if(holidays == null){
            holidays = new ArrayList<>();
        }
        holidays.add(holiday);
    }

    public void addDate(Date date){
        if(dates == null){
            dates = new ArrayList<>();
        }
        dates.add(date);
    }

    public void addDate(List<Date> dates){
        if(this.dates == null){
            this.dates = new ArrayList<>();
        }
        this.dates.addAll(dates);
    }
}
