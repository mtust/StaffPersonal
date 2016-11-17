package com.staff.personal.service;

import com.staff.personal.domain.Holiday;
import com.staff.personal.dto.HolidayDTO;
import com.staff.personal.dto.RestMessageDTO;

import java.util.List;

/**
 * Created by nazar on 17.11.16.
 */

public interface HolidayService {

    RestMessageDTO addHoliday(Long id, HolidayDTO holidayDTO);

    List<Holiday> getHolidays(Long id);




}
