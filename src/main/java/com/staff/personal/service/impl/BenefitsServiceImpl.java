package com.staff.personal.service.impl;

import com.staff.personal.domain.Benefits;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.BenefitsDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.BenefitsRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.BenefitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nazar on 14.11.16.
 */
@Service
@Slf4j
public class BenefitsServiceImpl implements BenefitsService {

    @Autowired
    StaffRepository staffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Autowired
    private BenefitsRepository benefitsRepository;

    @Override
    @Transactional
    public RestMessageDTO addBenefit(BenefitsDTO benefitsDTO, Long id) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + "dosen't exist");
        }
        Benefits benefits = new Benefits();
        List<Benefits> list = staff.getBenefits();
        try {
            benefits.setName(benefitsDTO.getName());
            benefits.setFromDate(simpleDateFormat.parse(benefitsDTO.getFromDate()));
            benefits.setToDate(simpleDateFormat.parse(benefitsDTO.getToDate()));
            benefits.setOrder(benefitsDTO.getOrder());
            benefits.setOrderDate(simpleDateFormat.parse(benefitsDTO.getOrderDate()));
            benefits.setCertification(benefitsDTO.getCertification());
            benefits.setPrivilege(benefitsDTO.getPrivilege());
            benefits.setActsAndComments(benefitsDTO.getActsAndComments());
            benefits.setOtherInfo(benefitsDTO.getOtherInfo());
            log.info(benefits.toString());
            list.add(benefits);
            staff.setBenefits(list);
            staffRepository.save(staff);
        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<Benefits> getBenefits(Long id) {
        return staffRepository.findOne(id).getBenefits();
    }

    @Override
    @Transactional
    public RestMessageDTO delBenefitsById(Long idStaff, Long idBen) {
        log.info("in delBenefitsById");
        Staff staff = staffRepository.findOne(idStaff);
        List<Benefits> list = staff.getBenefits();
        for (Benefits benefits : list) {
            if (benefits.getId()==(idBen)) {
                log.info("in foreach");
                list.remove(benefits);
                benefitsRepository.delete(benefits);
                log.info("list in foreach \n" +list.toString());
                break;
            }
        }
        log.info("list after foreach \n" + list.toString());
        staff.setBenefits(list);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }
}
