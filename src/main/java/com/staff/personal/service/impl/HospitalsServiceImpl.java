package com.staff.personal.service.impl;

import com.staff.personal.domain.Hospitals;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.HospitalsDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.HospitalsService;
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
public class HospitalsServiceImpl implements HospitalsService{

    @Autowired
    StaffRepository staffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @Override
    @Transactional
    public RestMessageDTO addHospitals(Long id, HospitalsDTO hospitalsDTo) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        Hospitals hospitals = new Hospitals();
        List<Hospitals> list = staff.getHospitals();
        try {
            hospitals.setTypeHospital(hospitalsDTo.getTypeHospital());
            hospitals.setFromDate(simpleDateFormat.parse(hospitalsDTo.getFromDate()));
            hospitals.setToDate(simpleDateFormat.parse(hospitalsDTo.getToDate()));
            hospitals.setDescription(hospitalsDTo.getDescription());
            log.info(hospitals.toString());
            list.add(hospitals);
            staff.setHospitals(list);
            staffRepository.save(staff);
        }catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<Hospitals> getHospitals(Long id) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        return staff.getHospitals();
    }
}
