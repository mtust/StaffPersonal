package com.staff.personal.service;

import com.staff.personal.domain.Holiday;
import com.staff.personal.dto.GroupedHolidayDTO;
import com.staff.personal.dto.HolidayDTO;
import com.staff.personal.dto.RestMessageDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by nazar on 17.11.16.
 */

public interface HolidayService {

    RestMessageDTO addHoliday(Long id, HolidayDTO holidayDTO);

    List<Holiday> getHolidays(Long id);

    RestMessageDTO delHoliday(Long idStaff, Long idHoliday);

    List<HolidayDTO> createHolidayDTO(List<Holiday> list);


    List<Holiday> updateHolidays(List<HolidayDTO> holidays);

    Map<Long, GroupedHolidayDTO> getGroupedHolidaysByStaffId(Long id);
}
