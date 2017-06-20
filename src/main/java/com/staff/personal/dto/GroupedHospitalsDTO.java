package com.staff.personal.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.staff.personal.domain.Holiday;
import com.staff.personal.domain.Hospitals;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class GroupedHospitalsDTO {
    private Long year;
    private List<Hospitals> hospitals;
    private Long totalDays;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private List<Date> dates;

    public void addHospital(Hospitals hospital){
        if(hospitals == null){
            hospitals = new ArrayList<>();
        }
        hospitals.add(hospital);
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
