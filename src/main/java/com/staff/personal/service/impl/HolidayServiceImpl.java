package com.staff.personal.service.impl;

import com.staff.personal.domain.Holiday;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.HolidayDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.HolidayService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nazar on 17.11.16.
 */
@Slf4j
@Service
public class HolidayServiceImpl implements HolidayService{

    @Autowired
    StaffRepository staffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @Override
    @Transactional
    public RestMessageDTO addHoliday(Long id, HolidayDTO holidayDTO) {
        Staff staff = staffRepository.findOne(id);
        Holiday holiday = new Holiday();
        List<Holiday> list = staff.getHolidays();
        try {
            holiday.setTypeHoliday(holidayDTO.getTypeHoliday());
            holiday.setHolidayPlace(holidayDTO.getHolidayPlace());
            holiday.setFromDate(simpleDateFormat.parse(holidayDTO.getFromDate()));
            holiday.setToDate(simpleDateFormat.parse(holidayDTO.getToDate()));
            holiday.setDescription(holidayDTO.getDescription());
            log.info("add holiday \n" + holiday.toString());
            list.add(holiday);
            staff.setHolidays(list);
            staffRepository.save(staff);

        }catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<Holiday> getHolidays(Long id) {
        return staffRepository.findOne(id).getHolidays();
    }
}