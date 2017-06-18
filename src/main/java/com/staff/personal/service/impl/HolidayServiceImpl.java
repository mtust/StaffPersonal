package com.staff.personal.service.impl;

import com.staff.personal.domain.Holiday;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.GroupedHolidayDTO;
import com.staff.personal.dto.HolidayDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.HolidayRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.HolidayService;
import com.staff.personal.util.MyDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nazar on 17.11.16.
 */
@Slf4j
@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private StaffRepository staffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    @Transactional
    public RestMessageDTO addHoliday(Long id, HolidayDTO holidayDTO) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        Holiday holiday = new Holiday();
        List<Holiday> list = staff.getHolidays();
        try {
            holiday.setTypeHoliday(holidayDTO.getTypeHoliday());
            holiday.setHolidayPlace(holidayDTO.getHolidayPlace());
            try {
                holiday.setFromDate(simpleDateFormat.parse(holidayDTO.getFromDate()));
                holiday.setToDate(simpleDateFormat.parse(holidayDTO.getToDate()));
            } catch (ParseException e) {
                holiday.setFromDate(simpleDateFormatNew.parse(holidayDTO.getFromDate()));
                holiday.setToDate(simpleDateFormatNew.parse(holidayDTO.getToDate()));
            }
            holiday.setDescription(holidayDTO.getDescription());
            log.info("add holiday \n" + holiday.toString());
            list.add(holiday);
            staff.setHolidays(list);
            staffRepository.save(staff);

        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<Holiday> getHolidays(Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        return staff.getHolidays();
    }

    @Override
    @Transactional
    public RestMessageDTO delHoliday(Long idStaff, Long idHoliday) {
        Staff staff = staffRepository.findOne(idStaff);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + idStaff + " dosen't exist");
        }
        List<Holiday> list = staff.getHolidays();
        for (Holiday holiday : list) {
            if (holiday.getId() == (idHoliday)) {
                list.remove(holiday);
                holidayRepository.delete(holiday);
                break;
            }
        }
        log.info("list after foreach \n" + list.toString());
        staff.setHolidays(list);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<HolidayDTO> createHolidayDTO(List<Holiday> list) {
        List<HolidayDTO> holidayDTOList = new ArrayList<>();
        for (Holiday holiday : list) {
            HolidayDTO holidayDTO = new HolidayDTO();
            holidayDTO.setId(holiday.getId().toString());
            holidayDTO.setDescription(holiday.getDescription());
            holidayDTO.setHolidayPlace(holiday.getHolidayPlace());
            holidayDTO.setTypeHoliday(holiday.getTypeHoliday());
            if (holiday.getFromDate() != null) {
                holidayDTO.setFromDate(simpleDateFormat.format(holiday.getFromDate()));
            }
            if (holiday.getToDate() != null) {
                holidayDTO.setToDate(simpleDateFormat.format(holiday.getToDate()));
            }
            holidayDTOList.add(holidayDTO);
        }
        return holidayDTOList;
    }

    @Override
    public List<Holiday> updateHolidays(List<HolidayDTO> holidayDTOS) {
        List<Holiday> holidayList = new ArrayList<>();

        for(HolidayDTO holidayDTO : holidayDTOS) {
            Holiday holiday = new Holiday();
            try {
                if(holidayDTO.getId() != null){
                    holiday.setId(Long.parseLong(holidayDTO.getId()));
                }
                holiday.setTypeHoliday(holidayDTO.getTypeHoliday());
                holiday.setHolidayPlace(holidayDTO.getHolidayPlace());
                try {
                    holiday.setFromDate(simpleDateFormat.parse(holidayDTO.getFromDate()));
                    holiday.setToDate(simpleDateFormat.parse(holidayDTO.getToDate()));
                } catch (ParseException e) {
                    holiday.setFromDate(simpleDateFormatNew.parse(holidayDTO.getFromDate()));
                    holiday.setToDate(simpleDateFormatNew.parse(holidayDTO.getToDate()));
                }
                holiday.setDescription(holidayDTO.getDescription());
                log.info("add holiday \n" + holiday.toString());
                holidayList.add(holiday);
            } catch (ParseException e) {
                log.warn(e.getMessage());
                throw new BadRequestParametersException("Дата у не вірному форматі");
            }
        }
        return holidayRepository.save(holidayList);
    }

    @Override
    public Map<Long, GroupedHolidayDTO> getGroupedHolidaysByStaffId(Long id) {
        List<Holiday> holidays = this.getHolidays(id);
        Collections.sort(holidays);
        Map<Long, GroupedHolidayDTO> groupedHolidayDTOMap = new HashMap<>();
        List<Holiday> holidaysFromPreviouseYear = new ArrayList<>();
        long currentYear = 0;
        GroupedHolidayDTO currentYearHoliday = new GroupedHolidayDTO();
        for(Holiday holiday: holidaysFromPreviouseYear){
            currentYear = holiday.getToDate().getYear();
            currentYearHoliday = new GroupedHolidayDTO();
            currentYearHoliday.addHoliday(holiday);
            Calendar cal = Calendar.getInstance();
            cal.set(holiday.getToDate().getYear(),0,1 );
            currentYearHoliday.addDate(MyDateUtil.getDaysBetweenDates(cal.getTime(), holiday.getToDate()));
            currentYearHoliday.setTotalDays((long) currentYearHoliday.getDates().size());
            groupedHolidayDTOMap.put(currentYear, currentYearHoliday);
        }
        for (Holiday holiday: holidays) {
            if(holiday.getFromDate().getYear() > currentYear) {
                if(currentYear != 0) {
                    groupedHolidayDTOMap.put(currentYear, currentYearHoliday);
                }
                currentYear = holiday.getFromDate().getYear();
                currentYearHoliday = new GroupedHolidayDTO();
            }
            currentYearHoliday.addHoliday(holiday);
            if(holiday.getFromDate().getYear() == (holiday.getToDate().getYear())){
                currentYearHoliday.addDate(MyDateUtil.getDaysBetweenDates(holiday.getFromDate(), holiday.getToDate()));
            } else {
                Calendar cal = Calendar.getInstance();
                cal.set(holiday.getFromDate().getYear(), 11, 31);
                currentYearHoliday.addDate(MyDateUtil.getDaysBetweenDates(holiday.getFromDate(), cal.getTime()));
                holidaysFromPreviouseYear.add(holiday);
            }
            currentYearHoliday.setTotalDays((long) currentYearHoliday.getDates().size());
        }
        return groupedHolidayDTOMap;
    }
}
