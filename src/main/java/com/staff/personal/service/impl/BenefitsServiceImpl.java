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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar on 14.11.16.
 */
@Service
@Slf4j
public class BenefitsServiceImpl implements BenefitsService {

    @Autowired
    private StaffRepository staffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM, dd, yyyy, hh:mm:ss aaa");
    @Autowired
    private BenefitsRepository benefitsRepository;

    @Override
    @Transactional
    public RestMessageDTO addBenefit(BenefitsDTO benefitsDTO, Long id) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        Benefits benefits = new Benefits();
        List<Benefits> list = staff.getBenefits();
        try {
            benefits.setName(benefitsDTO.getName());
            try {
                benefits.setFromDate(simpleDateFormat.parse(benefitsDTO.getFromDate()));
                benefits.setToDate(simpleDateFormat.parse(benefitsDTO.getToDate()));

                benefits.setOrderDate(simpleDateFormat.parse(benefitsDTO.getOrderDate()));
            } catch (ParseException e){
                benefits.setFromDate(simpleDateFormatNew.parse(benefitsDTO.getFromDate()));
                benefits.setToDate(simpleDateFormatNew.parse(benefitsDTO.getToDate()));

                benefits.setOrderDate(simpleDateFormatNew.parse(benefitsDTO.getOrderDate()));
            }
            if(benefitsDTO.getId() != null){
                benefits.setId(benefitsDTO.getId());
            }
            benefits.setOrder(benefitsDTO.getOrder());
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
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public List<BenefitsDTO> getBenefits(Long id) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        /*List<Benefits> benefitsList = staff.getBenefits();
        List<BenefitsDTO> list = new ArrayList<>();
        for (Benefits benefits : benefitsList) {
            BenefitsDTO benefitsDTO = new BenefitsDTO();
            benefitsDTO.setId(benefits.getId().toString());
            benefitsDTO.setName(benefits.getName());
            benefitsDTO.setFromDate(simpleDateFormat.format(benefits.getFromDate()));
            benefitsDTO.setToDate(simpleDateFormat.format(benefits.getToDate()));
            benefitsDTO.setOrder(benefits.getOrder());
            benefitsDTO.setOrderDate(simpleDateFormat.format(benefits.getOrderDate()));
            benefitsDTO.setCertification(benefits.getCertification());
            benefitsDTO.setPrivilege(benefits.getPrivilege());
            benefitsDTO.setActsAndComments(benefits.getActsAndComments());
            benefitsDTO.setOtherInfo(benefits.getOtherInfo());
            list.add(benefitsDTO);
        }*/
        return this.createBenefitsDTO(staff.getBenefits());
    }

    @Override
    @Transactional
    public RestMessageDTO delBenefitsById(Long idStaff, Long idBen) {
        log.info("in delBenefitsById");
        Staff staff = staffRepository.findOne(idStaff);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + idStaff + " dosen't exist or is deleted");
        }
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
        return new RestMessageDTO("Success", true);
    }

    @Override
    @Transactional
    public List<BenefitsDTO> createBenefitsDTO(List<Benefits> list){
        List<BenefitsDTO> benefitsDTOList = new ArrayList<>();
        for (Benefits benefits : list) {
            BenefitsDTO benefitsDTO = new BenefitsDTO();
            benefitsDTO.setId(benefits.getId());
            benefitsDTO.setName(benefits.getName());
            if (benefits.getFromDate() != null) {
            benefitsDTO.setFromDate(simpleDateFormat.format(benefits.getFromDate()));
            }
            if (benefits.getToDate() != null) {
                benefitsDTO.setToDate(simpleDateFormat.format(benefits.getToDate()));
            }
            benefitsDTO.setOrder(benefits.getOrder());
            if (benefits.getOrderDate() != null) {
                benefitsDTO.setOrderDate(simpleDateFormat.format(benefits.getOrderDate()));
            }
            benefitsDTO.setCertification(benefits.getCertification());
            benefitsDTO.setPrivilege(benefits.getPrivilege());
            benefitsDTO.setActsAndComments(benefits.getActsAndComments());
            benefitsDTO.setOtherInfo(benefits.getOtherInfo());
            benefitsDTOList.add(benefitsDTO);
        }
        return  benefitsDTOList;
    }

    @Override
    public List<Benefits> updateBenefits(List<BenefitsDTO> benefitsDTOS) {
        List<Benefits> benefitsList = new ArrayList<>();
        for(BenefitsDTO benefitsDTO : benefitsDTOS) {
            Benefits benefits = new Benefits();
            try {
                benefits.setName(benefitsDTO.getName());
                try {
                    benefits.setFromDate(simpleDateFormat.parse(benefitsDTO.getFromDate()));
                    benefits.setToDate(simpleDateFormat.parse(benefitsDTO.getToDate()));

                    benefits.setOrderDate(simpleDateFormat.parse(benefitsDTO.getOrderDate()));
                } catch (ParseException e) {
                    benefits.setFromDate(simpleDateFormatNew.parse(benefitsDTO.getFromDate()));
                    benefits.setToDate(simpleDateFormatNew.parse(benefitsDTO.getToDate()));

                    benefits.setOrderDate(simpleDateFormatNew.parse(benefitsDTO.getOrderDate()));
                }
                if (benefitsDTO.getId() != null) {
                    benefits.setId(benefitsDTO.getId());
                }
                benefits.setOrder(benefitsDTO.getOrder());
                benefits.setCertification(benefitsDTO.getCertification());
                benefits.setPrivilege(benefitsDTO.getPrivilege());
                benefits.setActsAndComments(benefitsDTO.getActsAndComments());
                benefits.setOtherInfo(benefitsDTO.getOtherInfo());
                log.info(benefits.toString());
                benefitsList.add(benefits);
            } catch (ParseException e) {
                log.warn(e.getMessage());
                throw new BadRequestParametersException("Дата у не вірному форматі");
            }
        }
        return benefitsRepository.save(benefitsList);
    }
}
