package com.staff.personal.controller.api;

import com.staff.personal.dto.GroupedHolidayDTO;
import com.staff.personal.dto.GroupedHospitalsDTO;
import com.staff.personal.service.HolidayService;
import com.staff.personal.service.HospitalsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/staff/grouped")
public class HolidayAndHospitalResources {
    @Autowired
    private HolidayService holidayService;

    @Autowired
    private HospitalsService hospitalsService;
    @RequestMapping("{id}/holiday")
    public Map<Long, GroupedHolidayDTO> getGroupedHolidays(@PathVariable("id") Long id){

        return holidayService.getGroupedHolidaysByStaffId(id);
    }

    @RequestMapping("{id}/hospital")
    public Map<Long, GroupedHospitalsDTO> getGroupedHospitals(@PathVariable("id") Long id){

        return hospitalsService.getGroupedHospitalsByStaffId(id);
    }

}
