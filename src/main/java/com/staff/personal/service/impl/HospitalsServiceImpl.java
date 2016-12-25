package com.staff.personal.service.impl;

import com.staff.personal.domain.Hospitals;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.HospitalsDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.HospitalsRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.HospitalsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar on 17.11.16.
 */
@Slf4j
@Service
public class HospitalsServiceImpl implements HospitalsService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private HospitalsRepository hospitalsRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");

    @Override
    @Transactional
    public RestMessageDTO addHospitals(Long id, HospitalsDTO hospitalsDTo) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        Hospitals hospitals = new Hospitals();
        List<Hospitals> list = staff.getHospitals();
        try {
            hospitals.setTypeHospital(hospitalsDTo.getTypeHospital());
            try {
                hospitals.setFromDate(simpleDateFormat.parse(hospitalsDTo.getFromDate()));
                hospitals.setToDate(simpleDateFormat.parse(hospitalsDTo.getToDate()));
            } catch (ParseException e) {

                hospitals.setFromDate(simpleDateFormatNew.parse(hospitalsDTo.getFromDate()));
                hospitals.setToDate(simpleDateFormatNew.parse(hospitalsDTo.getToDate()));
            }
            hospitals.setDescription(hospitalsDTo.getDescription());
            log.info(hospitals.toString());
            list.add(hospitals);
            staff.setHospitals(list);
            staffRepository.save(staff);
        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<Hospitals> getHospitals(Long id) {
        Staff staff = staffRepository.findOne(id);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        return staff.getHospitals();
    }

    @Override
    @Transactional
    public RestMessageDTO delHospitals(Long idStaff, Long idHosp) {
        Staff staff = staffRepository.findOne(idStaff);
        if (staff == null || staff.getIsDeleted() == true) {
            throw new ObjectDoNotExistException("staff object with id = " + idStaff + " dosen't exist or is deleted");
        }
        List<Hospitals> list = staff.getHospitals();
        for (Hospitals hospitals : list) {
            if (hospitals.getId() == idHosp) {
                list.remove(hospitals);
                hospitalsRepository.delete(hospitals);
                break;
            }
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<HospitalsDTO> createHospitalsDTO(List<Hospitals> list) {
        List<HospitalsDTO> hospitalsDTOList = new ArrayList<>();
        for (Hospitals hospitals : list) {
            HospitalsDTO hospitalsDTO = new HospitalsDTO();
            hospitalsDTO.setId(hospitals.getId().toString());
            hospitalsDTO.setTypeHospital(hospitals.getTypeHospital());
            hospitalsDTO.setDescription(hospitals.getDescription());
            if (hospitals.getFromDate() != null) {
                hospitalsDTO.setFromDate(simpleDateFormat.format(hospitals.getFromDate()));
            }
            if (hospitals.getToDate() != null) {
                hospitalsDTO.setToDate(simpleDateFormat.format(hospitals.getToDate()));
            }
        }

        return hospitalsDTOList;
    }

    @Override
    public List<Hospitals> updateHospitals(List<HospitalsDTO> hospitalsDTOS) {
        List<Hospitals> hospitalsList = new ArrayList<>();
        for(HospitalsDTO hospitalsDTO : hospitalsDTOS) {
            Hospitals hospitals = new Hospitals();
            try {
                if(hospitalsDTO.getId() != null) {
                    hospitals.setId(Long.parseLong(hospitalsDTO.getId()));
                }
                hospitals.setTypeHospital(hospitalsDTO.getTypeHospital());
                try {
                    hospitals.setFromDate(simpleDateFormat.parse(hospitalsDTO.getFromDate()));
                    hospitals.setToDate(simpleDateFormat.parse(hospitalsDTO.getToDate()));
                } catch (ParseException e) {

                    hospitals.setFromDate(simpleDateFormatNew.parse(hospitalsDTO.getFromDate()));
                    hospitals.setToDate(simpleDateFormatNew.parse(hospitalsDTO.getToDate()));
                }
                hospitals.setDescription(hospitalsDTO.getDescription());
                log.info(hospitals.toString());
                hospitalsList.add(hospitals);
            } catch (ParseException e) {
                log.warn(e.getMessage());
                throw new BadRequestParametersException("Дата у не вірному форматі");
            }
        }
        return hospitalsRepository.save(hospitalsList);
    }
}
